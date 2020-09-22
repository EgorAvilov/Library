package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ViewAllBorrowRecordsByUserCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        HttpSession session = request.getSession();

        int currentPage = Integer.parseInt(request.getParameter(ParameterName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParameterName.RECORDS_PER_PAGE));

        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();
        List<BorrowRecord> borrowRecords;
        int role = (int) session.getAttribute(ParameterName.ROLE);
        int userId = (int) session.getAttribute(ParameterName.USER_ID);
        try {
            if (valueChecker.isUser(role)) {
                int numberOfRows = userService.getNumberOfBorrowRecordRows(userId);
                int numberOfPages = numberOfRows / recordsPerPage;
                if (numberOfRows % recordsPerPage > 0) {
                    numberOfPages++;
                }
                borrowRecords = userService.getAllBorrowRecords(userId, currentPage, recordsPerPage);
                request.setAttribute(ParameterName.BORROW_RECORDS, borrowRecords);
                request.setAttribute(ParameterName.NUMBER_OF_PAGES, numberOfPages);
                request.setAttribute(ParameterName.CURRENT_PAGE, currentPage);
                request.setAttribute(ParameterName.RECORDS_PER_PAGE, recordsPerPage);
                path = pathCreator.getBooksPage();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
