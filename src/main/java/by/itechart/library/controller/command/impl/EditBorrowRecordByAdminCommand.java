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
        if(request.getParameter(ParameterName.STATUS)==null){
            throw new CommandException("Status cant be empty");
        }
        BorrowRecordStatus borrowRecordStatus = BorrowRecordStatus.valueOf(request.getParameter(ParameterName.STATUS));
        String comment = request.getParameter(ParameterName.COMMENT);

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setId(borrowRecordId);
        borrowRecord.setRecordStatus(borrowRecordStatus);
        borrowRecord.setComment(comment);

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole()
                       .getRoleId();
        try {
            if (valueChecker.isAdmin(role)) {
                adminService.updateBorrowRecord(borrowRecord);
                path = pathCreator.getBorrowRecordPage(request.getContextPath(), borrowRecordId);
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
