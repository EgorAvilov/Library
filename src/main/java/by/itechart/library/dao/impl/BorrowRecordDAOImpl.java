package by.itechart.library.dao.impl;

import by.itechart.library.dao.SQLRequest;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.exception.ConnectionPoolException;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.dao.pool.DBConnectionPool;
import by.itechart.library.dao.util.DAOUtilFactory;
import by.itechart.library.dao.util.api.ResourceCloser;
import by.itechart.library.dao.util.api.ResultCreator;
import by.itechart.library.dao.util.api.StatementInitializer;
import by.itechart.library.entity.BorrowRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRecordDAOImpl implements BorrowRecordDAO {
    private DAOUtilFactory utilFactory = DAOUtilFactory.getInstance();
    private ResultCreator resultCreator = utilFactory.getResultCreator();
    private ResourceCloser resourceCloser = utilFactory.getResourceCloser();
    private StatementInitializer statementInitializer = utilFactory.getStatementInitializer();
    private DBConnectionPool dbConnectionPool = DBConnectionPool.getInstance();

    @Override
    public List<BorrowRecord> getAll(int currentPage, int recordsPerPage) throws DAOException {
        String request = SQLRequest.GET_ALL_BORROW_RECORDS;
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addPaginationParameters(statement, start, recordsPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BorrowRecord borrowRecord = resultCreator.getNextBorrowRecord(resultSet);
                borrowRecords.add(borrowRecord);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);
        }
        return borrowRecords;
    }

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) throws DAOException {
        String request = SQLRequest.CREATE_BORROW_RECORD;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addBorrowRecord(statement, borrowRecord);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(statement);
        }
    }

    @Override
    public List<BorrowRecord> getAllByUserId(long userId, int currentPage, int recordsPerPage) throws DAOException {
        String request = SQLRequest.GET_BORROW_RECORDS_BY_USER_ID;
        int start = currentPage * recordsPerPage - recordsPerPage;
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addPaginationParameters(statement, userId, start, recordsPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                BorrowRecord borrowRecord = resultCreator.getNextBorrowRecord(resultSet);
                borrowRecords.add(borrowRecord);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);
        }
        return borrowRecords;
    }

    @Override
    public void updateBorrowRecordByAdmin(BorrowRecord borrowRecord) throws DAOException {
        String request = SQLRequest.UPDATE_BORROW_RECORD_BY_ADMIN;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.updateBorrowRecordByAdmin(statement, borrowRecord);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
    }

    @Override
    public void updateBorrowRecordByUser(BorrowRecord borrowRecord) throws DAOException {
        String request = SQLRequest.UPDATE_BORROW_RECORD_BY_USER;
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.updateBorrowRecordByUser(statement, borrowRecord);
            statement.executeQuery();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            resourceCloser.close(statement);
        }
    }

    @Override
    public BorrowRecord getBorrowRecord(long id) throws DAOException {
        String request = SQLRequest.GET_BORROW_RECORD_BY_ID;
        BorrowRecord borrowRecord = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addId(statement, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                borrowRecord = resultCreator.getNextBorrowRecord(resultSet);
            }
        } catch (SQLException | ConnectionPoolException ex) {
            throw new DAOException(ex);
        } finally {
            resourceCloser.close(resultSet);
            resourceCloser.close(statement);

        }
        return borrowRecord;
    }

    @Override
    public int getNumberOfRowsByAdmin() throws DAOException {
        String request = SQLRequest.COUNT_ROWS_OF_BORROW_RECORDS_BY_ADMIN;
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int numberOfRows = 0;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            resultSet = statement.executeQuery();
            resultSet.last();
            numberOfRows = resultSet.getRow();
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
    public int getNumberOfRowsByUser(long userId) throws DAOException {
        String request = SQLRequest.COUNT_ROWS_OF_BORROW_RECORDS_BY_USER;
        Connection connection;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int numberOfRows = 0;
        try {
            connection = dbConnectionPool.getConnection();
            statement = connection.prepareStatement(request);
            statementInitializer.addId(statement, userId);
            resultSet = statement.executeQuery();
            resultSet.last();
            numberOfRows = resultSet.getRow();
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
}
