package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Role;
import by.itechart.library.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class SignOutCommandTest {
    @InjectMocks
    SignOutCommand signOutCommand;

    @Mock
    ControllerUtilFactory utilFactory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    @Mock
    ControllerValueChecker valueChecker;
    @Mock
    HttpSession session;

    @Mock
    PathCreator pathCreator;
    User user;

    @Before
    public void init() {
        initMocks(this);
        user = new User();
        user.setRole(Role.USER);
    }

    @Test
    public void execute_hasRole() {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(valueChecker.isAnyUser(user.getRole()
                                                .getRoleId()))
               .thenReturn(true);
        String result = signOutCommand.execute(request, response);
        String expected = pathCreator.getSignIn();
        assertEquals(result, expected);
    }

    @Test
    public void execute_hasNoRole() {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(valueChecker.isAnyUser(user.getRole()
                                                .getRoleId()))
               .thenReturn(false);
        String result = signOutCommand.execute(request, response);
        String expected = pathCreator.getError();
        assertEquals(result, expected);
    }
}