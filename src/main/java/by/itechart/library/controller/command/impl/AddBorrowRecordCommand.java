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
import java.time.LocalDate;

public class AddBorrowRecordCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();

        String path = pathCreator.getError();
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute(ParameterName.USER_ID);
        int role = (int) session.getAttribute(ParameterName.ROLE);

        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = LocalDate.parse(request.getParameter(ParameterName.DUE_DATE));//проверить
        long bookId = Long.parseLong(request.getParameter(ParameterName.BOOK_ID));

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(bookId);
        borrowRecord.setUserId(userId);
        borrowRecord.setDueDate(dueDate);
        borrowRecord.setBorrowDate(borrowDate);
        try {
            if (valueChecker.isUser(role)) {
                userService.addBorrowRecord(borrowRecord);
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
