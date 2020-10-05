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
@Log4j
public class ViewBorrowRecordByUserCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String path;

        long borrowRecordId = Long.parseLong(request.getParameter(ParameterName.BORROW_RECORD_ID));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();

        BorrowRecord borrowRecord;
        try{
            if (valueChecker.isUser(role)) {
                borrowRecord = userService.getBorrowRecord(borrowRecordId);
                request.setAttribute(ParameterName.BORROW_RECORD, borrowRecord);
                log.info("viewing borrow record");
                path = pathCreator.getBorrowRecord();
            } else {
                path = pathCreator.getNoAccess();
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}
