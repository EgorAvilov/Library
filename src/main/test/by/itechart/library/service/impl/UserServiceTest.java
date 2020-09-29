package by.itechart.library.service.impl;

import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.PortableInterceptor.ServerIdHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    BookDAO bookDAO;

    @Mock
    UserDAO userDAO;

    @Mock
    BorrowRecordDAO borrowRecordDAO;

    @Mock
    BorrowRecord borrowRecord;

   @Before
   public void init(){
       initMocks(this);

   }
    @Test(expected = ServiceException.class)
    public void addBorrowRecord_DAOException_BookDAO_TakeBook() throws DAOException, ServiceException {
        long bookId = borrowRecord.getBookId();
        Mockito.doThrow(DAOException.class).when(bookDAO).takeBook(bookId);
        userService.addBorrowRecord(borrowRecord);
    }

    @Test(expected = ServiceException.class)
    public void addBorrowRecord_DAOException_BorrowRecordDAO_AddBorrowRecord() throws DAOException, ServiceException {
        long bookId = borrowRecord.getBookId();
        Mockito.doNothing().when(bookDAO).takeBook(bookId);
        Mockito.doThrow(DAOException.class).when(borrowRecordDAO).addBorrowRecord(borrowRecord);
        userService.addBorrowRecord(borrowRecord);
    }

    @Test
    public void addBorrowRecord_validParams() throws DAOException, ServiceException {
        long bookId = borrowRecord.getBookId();
        Mockito.doNothing().when(bookDAO).takeBook(bookId);
        Mockito.doNothing().when(borrowRecordDAO).addBorrowRecord(borrowRecord);
        userService.addBorrowRecord(borrowRecord);
    }


    @Test(expected = ServiceException.class)
    public void updateBorrowRecord_DAOException() throws DAOException, ServiceException {
       Mockito.doThrow(DAOException.class).when(borrowRecordDAO).updateBorrowRecordByUser(Mockito.any());
       userService.updateBorrowRecord(Mockito.any());
    }

    @Test
    public void updateBorrowRecord_validParams() throws DAOException, ServiceException {
        Mockito.doNothing().when(borrowRecordDAO).updateBorrowRecordByUser(Mockito.any());
        userService.updateBorrowRecord(Mockito.any());
    }

    @Test(expected = ServiceException.class)
    public void getAllBorrowRecords_DAOException() throws DAOException, ServiceException {
        Mockito.doThrow(DAOException.class)
               .when(borrowRecordDAO)
               .getAllByUserId(Mockito.anyLong(),Mockito.anyInt(), Mockito.anyInt());
        userService.getAllBorrowRecords(Mockito.anyLong(),Mockito.anyInt(), Mockito.anyInt());

    }

    @Test
    public void getAllBorrowRecords_validParams() throws DAOException, ServiceException {
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        Mockito.doReturn(borrowRecords)
               .when(borrowRecordDAO)
               .getAllByUserId(Mockito.anyLong(),Mockito.anyInt(), Mockito.anyInt());
        List<BorrowRecord> result = userService.getAllBorrowRecords(Mockito.anyInt(),Mockito.anyInt(), Mockito.anyInt());

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