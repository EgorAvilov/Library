package by.itechart.library.service.impl;

import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.UserValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommonServiceTest {

    @InjectMocks
    CommonServiceImpl commonService;

    @Mock
    BookDAO bookDAO;

    @Mock
    BorrowRecordDAO borrowRecordDAO;

    @Mock
    UserValidator userValidator;

    User user;
    Book book;
    BorrowRecord borrowRecord;

    @Mock
    private UserDAO userDAO;

    @Before
    public void init() {
        initMocks(this);
        user = new User();
        book=new Book();
        borrowRecord=new BorrowRecord();
    }

    @Test(expected = ServiceException.class)
    public void signIn_DAOException_UserDAO_GetUser() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .getUser(Mockito.anyString(), Mockito.anyString());
        commonService.signIn(Mockito.anyString(), Mockito.anyString());
    }

    @Test(expected = ServiceException.class)
    public void signIn_ValidatorException_User_DeletedStatus() throws ServiceException, ValidatorException, DAOException {
        Mockito.doReturn(user)
               .when(userDAO)
               .getUser(Mockito.anyString(), Mockito.anyString());
        Mockito.doThrow(ValidatorException.class)
               .when(userValidator)
               .validateUserDeletedStatus(user.isDeletedStatus());
        commonService.signIn(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void signIn_validParams() throws ServiceException, ValidatorException, DAOException {
        Mockito.doReturn(user)
               .when(userDAO)
               .getUser(Mockito.anyString(), Mockito.anyString());
        Mockito.doReturn(false)
               .when(userValidator)
               .validateUserDeletedStatus(user.isDeletedStatus());

        User result = commonService.signIn(Mockito.anyString(), Mockito.anyString());
        assertEquals(user, result);
    }

    @Test(expected = ServiceException.class)
    public void signUp_ValidatorException() throws ValidatorException, ServiceException {
        Mockito.doThrow(ValidatorException.class)
               .when(userValidator)
               .validateAdd(Mockito.any());
        commonService.signUp(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void signUp_DAOException() throws ValidatorException, ServiceException, DAOException {
        Mockito.doReturn(true)
               .when(userValidator)
               .validateAdd(Mockito.any());
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .addUser(Mockito.any());
        commonService.signUp(Mockito.any());
    }

    @Test
    public void signUp_validParams() throws ValidatorException, ServiceException, DAOException {
        Mockito.doReturn(true)
               .when(userValidator)
               .validateAdd(Mockito.any());
        Mockito.doNothing()
               .when(userDAO)
               .addUser(Mockito.any());

        commonService.signUp(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void getProfile_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .getUser(Mockito.anyLong());
        commonService.getProfile(Mockito.anyLong());
    }

    @Test
    public void getProfile_validParams() throws DAOException, ServiceException {
        Mockito.doReturn(user)
               .when(userDAO)
               .getUser(Mockito.anyLong());
        User result = commonService.getProfile(Mockito.anyLong());
        assertEquals(user, result);
    }

    @Test(expected = ServiceException.class)
    public void updateProfile_ValidatorException() throws ServiceException, ValidatorException {
        Mockito.doThrow(ValidatorException.class)
               .when(userValidator)
               .validateUpdate(Mockito.any());
        commonService.updateProfile(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void updateProfile_DAOException() throws ServiceException, ValidatorException, DAOException {
        Mockito.doReturn(true)
               .when(userValidator)
               .validateUpdate(Mockito.any());
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .updateUser(Mockito.any());
        commonService.updateProfile(Mockito.any());
    }

    @Test
    public void updateProfile_validParams() throws ServiceException, ValidatorException, DAOException {
        Mockito.doReturn(true)
               .when(userValidator)
               .validateUpdate(Mockito.any());
        Mockito.doNothing()
               .when(userDAO)
               .updateUser(Mockito.any());
        commonService.updateProfile(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void getAllBooks_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .getAllBooks(Mockito.anyInt(),Mockito.anyInt());
        commonService.getAllBooks(Mockito.anyInt(),Mockito.anyInt());
    }


    @Test
    public void getAllBooks_validParams() throws DAOException, ServiceException {
        List<Book> books=new ArrayList<>();
        Mockito.doReturn(books)
               .when(bookDAO)
               .getAllBooks(Mockito.anyInt(),Mockito.anyInt());
       List<Book> result= commonService.getAllBooks(Mockito.anyInt(),Mockito.anyInt());
       assertEquals(result,books);
    }

    @Test(expected = ServiceException.class)
    public void getBook_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .getBook(Mockito.anyLong());
        commonService.getBook(Mockito.anyLong());
    }

    @Test
    public void getBook_validParams() throws DAOException, ServiceException {
        Mockito.doReturn(book)
               .when(bookDAO)
               .getBook(Mockito.anyLong());
        Book result = commonService.getBook(Mockito.anyLong());
        assertEquals(book, result);
    }

    @Test(expected = ServiceException.class)
    public void getBorrowRecord_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getBorrowRecord(Mockito.anyLong());
        commonService.getBorrowRecord(Mockito.anyLong());
    }

    @Test
    public void getBorrowRecord_validParams() throws DAOException, ServiceException {
        Mockito.doReturn(borrowRecord)
               .when(borrowRecordDAO)
               .getBorrowRecord(Mockito.anyLong());
        BorrowRecord result = commonService.getBorrowRecord(Mockito.anyLong());
        assertEquals(borrowRecord, result);
    }
    @Test(expected = ServiceException.class)
    public void searchBooks_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .searchBooks(Mockito.anyString());
        commonService.searchBooks(Mockito.anyString());
    }


    @Test
    public void searchBooks_validParams() throws DAOException, ServiceException {
        List<Book> books=new ArrayList<>();
        Mockito.doReturn(books)
               .when(bookDAO)
               .searchBooks(Mockito.anyString());
        List<Book> result= commonService.searchBooks(Mockito.anyString());
        assertEquals(result,books);
    }

    @Test(expected = ServiceException.class)
    public void getNumberOfBookRows_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .getNumberOfRows();
        commonService.getNumberOfBookRows();

    }

    @Test
    public void getNumberOfBookRows_validParams() throws DAOException, ServiceException {
        int numberOfRows = 0;
        Mockito.doReturn(numberOfRows)
               .when(userDAO)
               .getNumberOfRows();

        int number = commonService.getNumberOfBookRows();
        assertEquals(numberOfRows, number);

    }
}