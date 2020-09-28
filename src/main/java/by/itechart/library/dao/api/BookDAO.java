package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;

import java.util.List;

public interface BookDAO {
    public void addBook(Book book) throws DAOException;

    public Book getBook(long id) throws DAOException;

    public void updateBook(Book book) throws DAOException;

    public void changeDeletedStatus(long bookId) throws DAOException;

    public List<Book> getAllBooks(int currentPage, int recordsPerPage) throws DAOException;

    public List<Book> searchBooks(String title, String authors, String genres, String description) throws DAOException;

    public int getNumberOfRows() throws DAOException;

    public boolean checkISBN(String ISBN, long id) throws DAOException;

    public void takeBook(long bookId) throws DAOException;

    public void returnBook(long bookId) throws DAOException;

    public int getAvailableAmountOfBooks(long bookId) throws DAOException;

    public void setAvailableAmountOfBooks(long bookId, int availableAmount) throws DAOException;

}
