package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.AdminService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j
public class ViewAllBorrowRecordsByAdminCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AdminService adminService = serviceFactory.getAdminService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        HttpSession session = request.getSession();

        int currentPage = Integer.parseInt(request.getParameter(ParameterName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParameterName.RECORDS_PER_PAGE));

        String path;
        List<BorrowRecord> borrowRecords;

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();
        try {
            if (valueChecker.isAdmin(role)) {
                int numberOfRows = adminService.getNumberOfBorrowRecordRows();
                int numberOfPages = numberOfRows / recordsPerPage;
                if (numberOfRows % recordsPerPage > 0) {
                    numberOfPages++;
                }
                borrowRecords = adminService.getAllBorrowRecords(currentPage, recordsPerPage);
                request.setAttribute(ParameterName.BORROW_RECORDS, borrowRecords);
                request.setAttribute(ParameterName.NUMBER_OF_PAGES, numberOfPages);
                request.setAttribute(ParameterName.CURRENT_PAGE, currentPage);
                request.setAttribute(ParameterName.RECORDS_PER_PAGE, recordsPerPage);
                path = pathCreator.getBorrowRecordsPage();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}
