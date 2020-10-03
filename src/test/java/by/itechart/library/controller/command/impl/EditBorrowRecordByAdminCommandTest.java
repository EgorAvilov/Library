package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.*;
import by.itechart.library.service.api.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EditBorrowRecordByAdminCommandTest {
    @InjectMocks
    EditBorrowRecordByAdminCommand editBorrowRecordByAdminCommand;

    @Mock
    ControllerUtilFactory utilFactory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    AdminService adminService;
    @Mock
    HttpSession session;
    @Mock
    ControllerValueChecker valueChecker;
    @Mock
    PathCreator pathCreator;

    @Mock
    BorrowRecord borrowRecord;
    User user;

    @Before
    public void init() {
        initMocks(this);
        user = new User();
        user.setRole(Role.ADMIN);
    }

    @Test
    public void execute_isRoleAdmin() throws CommandException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(request.getParameter(ParameterName.BORROW_RECORD_ID))
               .thenReturn(String.valueOf(1L));
        Mockito.when(request.getParameter(ParameterName.STATUS))
               .thenReturn(String.valueOf(BorrowRecordStatus.RETURNED));
        Mockito.when(request.getParameter(ParameterName.COMMENT))
               .thenReturn("comment");
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(valueChecker.isAdmin(user.getRole().getRoleId())).thenReturn(true);
        String result=editBorrowRecordByAdminCommand.execute(request,response);
        String expected= pathCreator.getBorrowRecordPage(request.getContextPath(),1);
        assertEquals(result,expected);

    }
    @Test
    public void execute_isRoleUser() throws CommandException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(request.getParameter(ParameterName.BORROW_RECORD_ID))
               .thenReturn(String.valueOf(1L));
        Mockito.when(request.getParameter(ParameterName.STATUS))
               .thenReturn(String.valueOf(BorrowRecordStatus.RETURNED));
        Mockito.when(request.getParameter(ParameterName.COMMENT))
               .thenReturn("comment");
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(valueChecker.isAdmin(user.getRole().getRoleId())).thenReturn(false);
        String result=editBorrowRecordByAdminCommand.execute(request,response);
        String expected= pathCreator.getError();
        assertEquals(result,expected);

    }
}