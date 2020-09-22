package by.itechart.library.service.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface AdminService {
    public void addBook(Book book) throws ServiceException;

    public void updateBook(Book book) throws ServiceException;

    public int changeBookDeletedStatus(long bookId) throws ServiceException;

    public int changeUserDeletedStatus(long userId) throws ServiceException;

    public List<BorrowRecord> getAllBorrowRecords(int currentPage, int recordsPerPage) throws ServiceException;

    public void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

    public List<User> getAllUsers(int currentPage, int recordsPerPage) throws ServiceException;

    public int getNumberOfBorrowRecordRows() throws ServiceException;

    public int getNumberOfUserRows() throws ServiceException;


}
