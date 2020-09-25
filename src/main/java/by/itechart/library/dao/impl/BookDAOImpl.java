package by.itechart.library.dao.impl;

import by.itechart.library.dao.SQLRequest;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.exception.ConnectionPoolException;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.dao.pool.DBConnectionPool;
import by.itechart.library.dao.util.DAOUtilFactory;
import by.itechart.library.dao.util.api.ResourceCloser;
import by.itechart.library.dao.util.api.ResultCreator;
import by.itechart.library.dao.util.api.StatementInitializer;
import by.itechart.library.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();
    private DAOUtilFactory utilFactory = DAOUtilFactory.getInstance();
    private ResultCreator resultCreator = utilFactory.getResultCreator();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();

    @Override
    public void addBook(Book book) throws DAOException {
        String request = SQLRequest.CREATE_BOOK;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addBook(statement, book);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(statement);
        }
    }

    @Override
    public Book getBook(long id) throws DAOException {
        String request = SQLRequest.GET_BOOK_BY_ID;
        Book book = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addId(statement, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = resultCreator.getNextBook(resultSet);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);

        }
        return book;
    }

    @Override
    public void updateBook(Book book) throws DAOException {
        String request = SQLRequest.UPDATE_BOOK;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.updateBook(statement, book);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
    }

    @Override
    public int changeDeletedStatus(long bookId) throws DAOException {
        String request = SQLRequest.CHANGE_BOOK_DELETED_STATUS;
        Connection connection;
        PreparedStatement statement = null;
        int result;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            Book book = getBook(bookId);
            statementInitializer.changeDeletedStatus(statement, !book.isDeletedStatus(), bookId);
            result = statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
        return result;
    }

    @Override
    public List<Book> getAllBooks(int currentPage, int recordsPerPage) throws DAOException {
        String request = SQLRequest.GET_ALL_BOOKS;
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<Book> books = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addPaginationParameters(statement, start, recordsPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = resultCreator.getNextBook(resultSet);
                books.add(book);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);

        }
        return books;
    }

    @Override
    public List<Book> searchBooks(String title, String authors, String genres, String description) throws DAOException {
        String request = SQLRequest.SEARCH_BOOKS_BY_PARAMETERS;
        List<Book> books = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addSearchParameters(statement, title, authors, genres, description);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = resultCreator.getNextBook(resultSet);
                books.add(book);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);
        }
        return books;
    }

    @Override
    public int getNumberOfRows() throws DAOException {
        String request = SQLRequest.COUNT_ROWS_OF_BOOKS;
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int numberOfRows = 0;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            resultSet.last();
            numberOfRows =resultSet.getInt(1);
            while (resultSet.next()) {
                numberOfRows++;
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);
        }
        return numberOfRows;
    }

    @Override
    public boolean checkISBN(String ISBN) throws DAOException {
        String request = SQLRequest.GET_ALL_BOOKS_BY_ISBN;
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addISBN(statement, ISBN);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                throw new DAOException("Your ISBN is not unique");
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);
        }
        return true;
    }

    @Override
    public void takeBook(long bookId) throws DAOException {
        int availableAmount = getAvailableAmountOfBooks(bookId);
        if(availableAmount<=0){
            throw new DAOException("No free books");
        }
        availableAmount--;
        setAvailableAmountOfBooks(bookId, availableAmount);
    }

    @Override
    public int getAvailableAmountOfBooks(long bookId) throws DAOException {
        String request = SQLRequest.GET_AVAILABLE_AMOUNT_OF_BOOKS_BY_ID;
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet;
        int availableAmount = 0;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addId(statement, bookId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                availableAmount = resultCreator.getNextBookAvailableAmount(resultSet);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
        return availableAmount;
    }

    @Override
    public void setAvailableAmountOfBooks(long bookId, int availableAmount) throws DAOException {
        String request = SQLRequest.CHANGE_BOOK_AVAILABLE_AMOUNT;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.updateBookAvailableAmount(statement, bookId, availableAmount);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
    }
}
