package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.service.api.CommonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SignInCommandTest {
    @InjectMocks
    SignInCommand signInCommand;

    @Mock
    ControllerUtilFactory utilFactory;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    CommonService commonService;
    @Mock
    HttpSession session;

    @Mock
    PathCreator pathCreator;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void execute() throws CommandException {
        Mockito.when(request.getParameter(ParameterName.USERNAME))
               .thenReturn("username");
        Mockito.when(request.getParameter(ParameterName.PASSWORD))
               .thenReturn("password");
        Mockito.when(request.getSession())
               .thenReturn(session);
        String result=signInCommand.execute(request,response);
        String expected= pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(result,expected);

    }
}