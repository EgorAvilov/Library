package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.Role;
import by.itechart.library.entity.User;
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class AddBorrowRecordCommandTest {
    @InjectMocks
    AddBorrowRecordCommand addBorrowRecordCommand;

    @Mock
    ControllerUtilFactory utilFactory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    UserService userService;
    @Mock
    HttpSession session;
    @Mock
    ControllerValueChecker valueChecker;
    @Mock
    PathCreator pathCreator;
    @Mock
    Command command;

    BorrowRecord borrowRecord;
    User user;

    @Before
    public void init() {
        initMocks(this);
        borrowRecord=new BorrowRecord();

        user = new User();
        user.setId(1);
        user.setRole(Role.USER);
    }

    @Test
    public void execute_validData() throws CommandException, ServiceException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(request.getParameter(ParameterName.DUE_DATE))
               .thenReturn("2020-09-09");
        Mockito.when(request.getParameter(ParameterName.BOOK_ID))
               .thenReturn(String.valueOf(1L));
        borrowRecord.setDueDate(LocalDate.parse("2020-09-09"));
        Mockito.doReturn(true)
               .when(valueChecker)
               .isUser(user.getRole()
                           .getRoleId());
        Mockito.doThrow(ServiceException.class)
               .when(userService)
               .addBorrowRecord(borrowRecord);

        String result=addBorrowRecordCommand.execute(request, response);
        String expected=pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(result,expected);
    }

    @Test
    public void execute_isRoleAdmin() throws CommandException, ServiceException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(request.getParameter(ParameterName.DUE_DATE))
               .thenReturn("2020-09-09");
        Mockito.when(request.getParameter(ParameterName.BOOK_ID))
               .thenReturn(String.valueOf(1L));
        borrowRecord.setDueDate(LocalDate.parse("2020-09-09"));
        Mockito.doReturn(false)
               .when(valueChecker)
               .isUser(user.getRole()
                           .getRoleId());
        Mockito.doThrow(ServiceException.class)
               .when(userService)
               .addBorrowRecord(borrowRecord);
        String result=addBorrowRecordCommand.execute(request, response);
        String expected=pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(result,expected);
    }
}