package by.itechart.library.service.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface CommonService {
     User signIn(String username, String password) throws ServiceException;

     void signUp(User user) throws ServiceException;

     User getProfile(long userId) throws ServiceException;

     void updateProfile(User user) throws ServiceException;

     List<Book> getAllBooks(int currentPage, int recordsPerPage) throws ServiceException;

     Book getBook(long bookId) throws ServiceException;

     BorrowRecord getBorrowRecord(long borrowRecordId) throws ServiceException;

     List<Book> searchBooks(String searchParameter) throws ServiceException;

     int getNumberOfBookRows() throws ServiceException;
}
