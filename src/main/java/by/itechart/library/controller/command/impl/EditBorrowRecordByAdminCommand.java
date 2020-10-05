package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.AdminService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class EditBorrowRecordByAdminCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AdminService adminService = serviceFactory.getAdminService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        String path;

        long borrowRecordId = Long.parseLong(request.getParameter(ParameterName.BORROW_RECORD_ID));
        long bookId=Long.parseLong(request.getParameter(ParameterName.BOOK_ID));
        if (request.getParameter(ParameterName.STATUS_ID) == null) {
            throw new CommandException("Status cant be empty");
        }
        int borrowRecordStatusId = Integer.parseInt(request.getParameter(ParameterName.STATUS_ID));

        BorrowRecordStatus borrowRecordStatus = BorrowRecordStatus.values()[borrowRecordStatusId - 1];
        String comment = request.getParameter(ParameterName.COMMENT);
        if (comment == null) {
            comment = "";
        }
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setId(borrowRecordId);
        borrowRecord.setRecordStatus(borrowRecordStatus);
        borrowRecord.setComment(comment);
        borrowRecord.setBookId(bookId);

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole()
                       .getRoleId();
        try {
            if (valueChecker.isAdmin(role)) {
                adminService.updateBorrowRecord(borrowRecord);
                log.info("updating borrow record by admin");
                path = pathCreator.getBorrowRecordPageAdmin(request.getContextPath());
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
