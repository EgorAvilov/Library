package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.dto.BorrowRecordDto;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Log4j
public class ViewAllBorrowRecordsByUserCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserService userService = serviceFactory.getUserService();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        String path;
        List<BorrowRecordDto> borrowRecords;
        User user = (User) session.getAttribute(ParameterName.USER);
        long userId= user.getId();
        int role = user.getRole().getRoleId();
        try {
            if (valueChecker.isUser(role)) {
                borrowRecords = userService.getAllBorrowRecords(userId );
                request.setAttribute(ParameterName.BORROW_RECORDS, borrowRecords);
                log.info("viewing all borrow record by user");
                path = pathCreator.getBorrowRecordsPage();
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
