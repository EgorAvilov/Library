package by.itechart.library.service.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface CommonService {
    public User signIn(String username, String password) throws ServiceException;

    public void signUp(User user) throws ServiceException;

    public User getProfile(long userId) throws ServiceException;

    public void updateProfile(User user) throws ServiceException;

    public List<Book> getAllBooks(int currentPage, int recordsPerPage) throws ServiceException;

    public Book getBook(long bookId) throws ServiceException;

    public BorrowRecord getBorrowRecord(long borrowRecordId) throws ServiceException;

    public List<Book> searchBooks(String title, String authors, String genres, String description) throws ServiceException;

    public int getNumberOfBookRows() throws ServiceException;
}
