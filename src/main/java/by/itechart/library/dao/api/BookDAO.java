package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;

import java.util.List;

public interface BookDAO {
     void addBook(Book book) throws DAOException;

     Book getBook(long id) throws DAOException;

     void updateBook(Book book) throws DAOException;

     void changeDeletedStatus(long bookId) throws DAOException;

     List<Book> getAllBooks() throws DAOException;

     List<Book> searchBooks(String searchParameter) throws DAOException;

     int getNumberOfRows() throws DAOException;

     boolean checkISBNtoAdd(String ISBN) throws DAOException;

     boolean checkISBNtoUpdate(String ISBN, long id) throws DAOException;

     void takeBook(long bookId) throws DAOException;

     void returnBook(long bookId) throws DAOException;

     int getAvailableAmountOfBooks(long bookId) throws DAOException;

     void setAvailableAmountOfBooks(long bookId, int availableAmount) throws DAOException;

}
