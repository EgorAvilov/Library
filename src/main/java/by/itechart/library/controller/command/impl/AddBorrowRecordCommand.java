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
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Log4j
public class AddBorrowRecordCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        String path;

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole()
                       .getRoleId();
        long userId = user.getId();

        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = null;
        try {
            dueDate = LocalDate.parse(request.getParameter(ParameterName.DUE_DATE));
        } catch (NullPointerException e) {
            request.setAttribute("message.empty_dueDate", e.getMessage());
        }
        long bookId = Long.parseLong(request.getParameter(ParameterName.BOOK_ID));

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(bookId);
        borrowRecord.setUserId(userId);
        borrowRecord.setDueDate(dueDate);
        borrowRecord.setBorrowDate(borrowDate);
        try {
            if (valueChecker.isUser(role)) {
                userService.addBorrowRecord(borrowRecord);
                log.info("adding borrow record");
                path = pathCreator.getForwardMainPage(request.getContextPath());
            } else {
                path = pathCreator.getNoAccess();
            }
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}
