package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.Role;
import by.itechart.library.entity.User;
import by.itechart.library.service.api.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class EditBookCommandTest {

    @InjectMocks
    EditBookCommand editBookCommand;

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
    Part coverPart;
    @Mock
    InputStream cover;
    @Mock
    Book book;
    User user;

    @Before
    public void init() {
        initMocks(this);
        user = new User();
        user.setRole(Role.ADMIN);
    }


    @Test
    public void execute_isPhotoAndSizeSuitsAndRoleAdmin() throws CommandException, IOException, ServletException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(request.getParameter(ParameterName.BOOK_ID))
               .thenReturn(String.valueOf(1L));
        Mockito.when(request.getParameter(ParameterName.TITLE))
               .thenReturn("Title");
        Mockito.when(request.getParameter(ParameterName.AUTHORS))
               .thenReturn("Authors");
        Mockito.when(request.getParameter(ParameterName.PUBLISHER))
               .thenReturn("Publisher");
        Mockito.when(request.getParameter(ParameterName.PUBLISH_DATE))
               .thenReturn("2020-11-11");
        Mockito.when(request.getParameter(ParameterName.GENRES))
               .thenReturn("Genres");
        Mockito.when(request.getParameter(ParameterName.PAGE_COUNT))
               .thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ISBN))
               .thenReturn("123-123-123");
        Mockito.when(request.getParameter(ParameterName.DESCRIPTION))
               .thenReturn("Description");
        Mockito.when(request.getParameter(ParameterName.TOTAL_AMOUNT))
               .thenReturn("1");
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(request.getPart(ParameterName.COVER))
               .thenReturn(coverPart);
        Mockito.when(valueChecker.isPhoto(coverPart))
               .thenReturn(true);
        Mockito.when(valueChecker.suitsSize(coverPart.getSize()))
               .thenReturn(true);
        Mockito.when(editBookCommand.getInputStream(coverPart))
               .thenReturn(cover);
        Mockito.when(valueChecker.isAdmin(user.getRole().getRoleId())).thenReturn(true);
        String result=editBookCommand.execute(request,response);
        String expected= pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(result,expected);
    }

    @Test
    public void execute_isNotPhotoAndRoleUser() throws CommandException, IOException, ServletException {
        Mockito.when(request.getSession())
               .thenReturn(session);
        Mockito.when(request.getParameter(ParameterName.BOOK_ID))
               .thenReturn(String.valueOf(1L));
        Mockito.when(request.getParameter(ParameterName.TITLE))
               .thenReturn("Title");
        Mockito.when(request.getParameter(ParameterName.AUTHORS))
               .thenReturn("Authors");
        Mockito.when(request.getParameter(ParameterName.PUBLISHER))
               .thenReturn("Publisher");
        Mockito.when(request.getParameter(ParameterName.PUBLISH_DATE))
               .thenReturn("2020-11-11");
        Mockito.when(request.getParameter(ParameterName.GENRES))
               .thenReturn("Genres");
        Mockito.when(request.getParameter(ParameterName.PAGE_COUNT))
               .thenReturn("1");
        Mockito.when(request.getParameter(ParameterName.ISBN))
               .thenReturn("123-123-123");
        Mockito.when(request.getParameter(ParameterName.DESCRIPTION))
               .thenReturn("Description");
        Mockito.when(request.getParameter(ParameterName.TOTAL_AMOUNT))
               .thenReturn("1");
        Mockito.when(session.getAttribute(ParameterName.USER))
               .thenReturn(user);
        Mockito.when(request.getPart(ParameterName.COVER))
               .thenReturn(coverPart);
        Mockito.when(valueChecker.isPhoto(coverPart))
               .thenReturn(false);
        Mockito.when(valueChecker.suitsSize(coverPart.getSize()))
               .thenReturn(false);
        Mockito.when(editBookCommand.getInputStream(coverPart))
               .thenReturn(cover);
        Mockito.when(valueChecker.isAdmin(user.getRole().getRoleId())).thenReturn(false);
        String result=editBookCommand.execute(request,response);
        String expected= pathCreator.getForwardMainPage(request.getContextPath());
        assertEquals(result,expected);

    }
}