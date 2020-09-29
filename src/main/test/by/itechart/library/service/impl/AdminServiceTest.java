package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.dao.impl.BookDAOImpl;
import by.itechart.library.dao.impl.BorrowRecordDAOImpl;
import by.itechart.library.dao.impl.UserDAOImpl;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.entity.User;
import by.itechart.library.service.UtilFactory;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.BookValidator;
import by.itechart.library.service.util.BorrowRecordValidator;
import by.itechart.library.service.util.impl.BookValidatorImpl;
import by.itechart.library.service.util.impl.BorrowRecordValidatorImpl;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AdminServiceTest {
    @InjectMocks
    AdminServiceImpl adminService;
/*
    DAOFactory daoFactory = Mockito.mock(DAOFactory.class);
    BookDAO bookDAO = Mockito.mock(BookDAOImpl.class);
    UserDAO userDAO = Mockito.mock(UserDAOImpl.class);
    BorrowRecordDAO borrowRecordDAO = Mockito.mock(BorrowRecordDAOImpl.class);
    UtilFactory utilFactory = Mockito.mock(UtilFactory.class);
    BookValidator bookValidator = Mockito.mock(BookValidatorImpl.class);
    BorrowRecordValidator borrowRecordValidator = Mockito.mock(BorrowRecordValidatorImpl.class);*/
@Mock
    DAOFactory daoFactory;
    @Mock
    BookDAO bookDAO;
    @Mock
    UserDAO userDAO;
    @Mock
    BorrowRecordDAO borrowRecordDAO;
    @Mock
    UtilFactory utilFactory;
    @Mock
    BookValidator bookValidator;
    @Mock
    BorrowRecordValidator borrowRecordValidator;
    Book book = new Book();
    BorrowRecord borrowRecord = new BorrowRecord();


    @Before
    public void init() {
        initMocks(this);
        //adminService = new AdminServiceImpl(daoFactory, bookDAO, userDAO, borrowRecordDAO, utilFactory, bookValidator, borrowRecordValidator);

    }

    @Test(expected = ServiceException.class)
    public void addBook_ValidatorException() throws ValidatorException, ServiceException {
        Mockito.doThrow(ValidatorException.class)
               .when(bookValidator)
               .validateAdd(Mockito.any());

        adminService.addBook(Mockito.any());

    }

    @Test
    public void addBook_validParams() throws ValidatorException, ServiceException {
        when(bookValidator.validateAdd(Mockito.any())).thenReturn(true);
        adminService.addBook(Mockito.any());

    }

    @Test
    public void addBook_DAOException() throws DAOException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .addBook(book);
    }


    @Test(expected = ServiceException.class)
    public void updateBook_ValidatorException() throws ValidatorException, ServiceException {
        Mockito.doThrow(ValidatorException.class)
               .when(bookValidator)
               .validateUpdate(Mockito.any());
        adminService.updateBook(Mockito.any());
    }


    @Test
    public void updateBook_DAOException() throws DAOException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .updateBook(book);
    }


    @Test
    public void updateBook_validParams() throws ValidatorException, ServiceException {
        when(bookValidator.validateUpdate(Mockito.any())).thenReturn(true);
        adminService.updateBook(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void changeBookDeletedStatus_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .changeDeletedStatus(1);
        adminService.changeBookDeletedStatus(1);
    }

    @Test
    public void changeBookDeletedStatus_validParams() throws ServiceException, DAOException {
        Mockito.doNothing()
               .when(bookDAO)
               .changeDeletedStatus(Mockito.anyInt());
        adminService.changeBookDeletedStatus(Mockito.anyInt());
    }

    @Test(expected = ServiceException.class)
    public void changeUserDeletedStatus_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .changeDeletedStatus(1);
        adminService.changeUserDeletedStatus(1);
    }

    @Test
    public void changeUserDeletedStatus_validParams() throws ServiceException, DAOException {
        Mockito.doNothing()
               .when(userDAO)
               .changeDeletedStatus(Mockito.anyInt());
        adminService.changeUserDeletedStatus(Mockito.anyInt());
    }

    @Test(expected = ServiceException.class)
    public void getAllBorrowRecords_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getAll(1, 1);
        adminService.getAllBorrowRecords(1, 1);
    }

    @Test
    public void getAllBorrowRecords_validParams() throws DAOException, ServiceException {
        when(borrowRecordDAO.getAll(1, 1)).thenReturn(Collections.emptyList());
        adminService.getAllBorrowRecords(1, 1);


    }

    @Test(expected = ServiceException.class)
    public void updateBorrowRecord_ValidatorException() throws ValidatorException, ServiceException {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecord.setUserId(1);
        long bookId = borrowRecord.getBookId();
        BorrowRecordStatus borrowRecordStatus = borrowRecord.getRecordStatus();
        int borrowRecordStatusId = borrowRecordStatus.getBorrowRecordStatusId();
        Mockito.doThrow(ValidatorException.class)
               .when(borrowRecordValidator)
               .validateStatus(borrowRecordStatusId);
        adminService.updateBorrowRecord(borrowRecord);
    }


    @Test
    public void updateBorrowRecord_validParams() throws DAOException, ValidatorException, ServiceException {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecord.setUserId(1);
        long bookId = borrowRecord.getBookId();
        BorrowRecordStatus borrowRecordStatus = borrowRecord.getRecordStatus();
        int borrowRecordStatusId = borrowRecordStatus.getBorrowRecordStatusId();
        Mockito.doReturn(true)
               .when(borrowRecordValidator)
               .validateStatus(Mockito.anyInt());
        Mockito.doNothing()
               .when(bookDAO)
               .returnBook(bookId);
        adminService.updateBorrowRecord(borrowRecord);
    }


    @Test
    public void updateBorrowRecord_DAOException() throws ValidatorException, ServiceException, DAOException {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecord.setUserId(1);
        long bookId = borrowRecord.getBookId();
        BorrowRecordStatus borrowRecordStatus = borrowRecord.getRecordStatus();
        int borrowRecordStatusId = borrowRecordStatus.getBorrowRecordStatusId();
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .returnBook(Mockito.anyInt());
        adminService.updateBorrowRecord(borrowRecord);
    }

    @Test(expected = ServiceException.class)
    public void getAllUsers_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .getAll(Mockito.anyInt(), Mockito.anyInt());
        adminService.getAllUsers(Mockito.anyInt(), Mockito.anyInt());

    }

    @Test
    public void getAllUsers_validParams() throws DAOException, ServiceException {
        List<User> users = new ArrayList<>();
        Mockito.doReturn(users)
               .when(userDAO)
               .getAll(Mockito.anyInt(), Mockito.anyInt());
        List<User> result = adminService.getAllUsers(Mockito.anyInt(), Mockito.anyInt());

        assertEquals(result, users);
    }


    @Test(expected = ServiceException.class)
    public void getNumberOfBorrowRecordRows_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getNumberOfRowsByAdmin();
        adminService.getNumberOfBorrowRecordRows();

    }

    @Test
    public void getNumberOfBorrowRecordRows_validParams() throws DAOException, ServiceException {
        int numberOfRows = 0;
        Mockito.doReturn(numberOfRows)
               .when(borrowRecordDAO)
               .getNumberOfRowsByAdmin();
        int number = adminService.getNumberOfBorrowRecordRows();
        assertEquals(numberOfRows, number);

    }

    @Test(expected = ServiceException.class)
    public void getNumberOfUserRows_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(userDAO)
               .getNumberOfRows();
        adminService.getNumberOfUserRows();

    }

    @Test
    public void getNumberOfUserRows_validParams() throws DAOException, ServiceException {
        int numberOfRows = 0;
        Mockito.doReturn(numberOfRows)
               .when(userDAO)
               .getNumberOfRows();

        int number = adminService.getNumberOfUserRows();
        assertEquals(numberOfRows, number);

    }
}