package by.itechart.library.service.impl;

import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.dto.BorrowRecordDto;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.BorrowRecordValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    BookDAO bookDAO;

    @Mock
    BorrowRecordDAO borrowRecordDAO;

    @Mock
    BorrowRecord borrowRecord;

    @Mock
    BorrowRecordValidator borrowRecordValidator;

    @Before
    public void init() {
        initMocks(this);

    }

    @Test(expected = ServiceException.class)
    public void addBorrowRecord_ValidatorException_BookDAO_TakeBook() throws DAOException, ServiceException, ValidatorException {
        long bookId = borrowRecord.getBookId();
        Mockito.doThrow(ValidatorException.class)
               .when(borrowRecordValidator)
               .validateAdd(borrowRecord);
        userService.addBorrowRecord(borrowRecord);
    }

    @Test(expected = ServiceException.class)
    public void addBorrowRecord_DAOException_BookDAO_TakeBook() throws DAOException, ServiceException, ValidatorException {
        long bookId = borrowRecord.getBookId();
        Mockito.doReturn(true)
               .when(borrowRecordValidator)
               .validateAdd(borrowRecord);
        Mockito.doThrow(DAOException.class)
               .when(bookDAO)
               .takeBook(bookId);
        userService.addBorrowRecord(borrowRecord);
    }

    @Test(expected = ServiceException.class)
    public void addBorrowRecord_DAOException_BorrowRecordDAO_AddBorrowRecord() throws DAOException, ServiceException, ValidatorException {
        long bookId = borrowRecord.getBookId();
        Mockito.doReturn(true)
               .when(borrowRecordValidator)
               .validateAdd(borrowRecord);
        Mockito.doNothing()
               .when(bookDAO)
               .takeBook(bookId);
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .addBorrowRecord(borrowRecord);
        userService.addBorrowRecord(borrowRecord);
    }

    @Test
    public void addBorrowRecord_validParams() throws DAOException, ServiceException, ValidatorException {
        long bookId = borrowRecord.getBookId();
        Mockito.doReturn(true)
               .when(borrowRecordValidator)
               .validateAdd(borrowRecord);
        Mockito.doNothing()
               .when(bookDAO)
               .takeBook(bookId);
        Mockito.doNothing()
               .when(borrowRecordDAO)
               .addBorrowRecord(borrowRecord);
        userService.addBorrowRecord(borrowRecord);
    }


    @Test(expected = ServiceException.class)
    public void updateBorrowRecord_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .updateBorrowRecordByUser(Mockito.any());
        userService.updateBorrowRecord(Mockito.any());
    }

    @Test
    public void updateBorrowRecord_validParams() throws DAOException, ServiceException {
        Mockito.doNothing()
               .when(borrowRecordDAO)
               .updateBorrowRecordByUser(Mockito.any());
        userService.updateBorrowRecord(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void getABorrowRecord_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getAllByUserIdNew(Mockito.anyLong());
        userService.getAllBorrowRecords(Mockito.anyLong());

    }

    @Test
    public void getBorrowRecord_validParams() throws DAOException, ServiceException {
        BorrowRecord borrowRecord=new BorrowRecord();
        Mockito.doReturn(borrowRecord)
               .when(borrowRecordDAO)
               .getBorrowRecord(Mockito.anyLong());
       userService.getAllBorrowRecords(Mockito.anyLong());


    }



    @Test(expected = ServiceException.class)
    public void getAllBorrowRecords_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getAllByUserIdNew(Mockito.anyLong());
        userService.getAllBorrowRecords(Mockito.anyLong());

    }

    @Test
    public void getAllBorrowRecords_validParams() throws DAOException, ServiceException {
        List<BorrowRecordDto> borrowRecords = new ArrayList<>();
        Mockito.doReturn(borrowRecords)
               .when(borrowRecordDAO)
               .getAllByUserId(Mockito.anyLong());
        List<BorrowRecordDto> result = userService.getAllBorrowRecords(Mockito.anyLong());

        assertEquals(result, borrowRecords);
    }

    @Test(expected = ServiceException.class)
    public void getNumberOfBorrowRecordRows_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getNumberOfRowsByUser(Mockito.anyLong());
        userService.getNumberOfBorrowRecordRows(Mockito.anyLong());

    }

    @Test
    public void getNumberOfBorrowRecordRows_validParams() throws DAOException, ServiceException {
        int numberOfRows = 0;
        Mockito.doReturn(numberOfRows)
               .when(borrowRecordDAO)
               .getNumberOfRowsByUser(Mockito.anyLong());

        int number = userService.getNumberOfBorrowRecordRows(Mockito.anyLong());
        assertEquals(numberOfRows, number);

    }
}