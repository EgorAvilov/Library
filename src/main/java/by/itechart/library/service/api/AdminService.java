package by.itechart.library.service.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.dto.BorrowRecordDto;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface AdminService {
    void addBook(Book book) throws ServiceException;

    void updateBook(Book book) throws ServiceException;

    void changeBookDeletedStatus(long bookId) throws ServiceException;

    void changeUserDeletedStatus(long userId) throws ServiceException;

    List<BorrowRecordDto> getAllBorrowRecords() throws ServiceException;

    void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    int getNumberOfBorrowRecordRows() throws ServiceException;

    int getNumberOfUserRows() throws ServiceException;

    void changeUserRole(long userId) throws ServiceException;


}
