package by.itechart.library.dao.util.impl;

import by.itechart.library.dao.util.api.StatementInitializer;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class StatementInitializerImpl implements StatementInitializer {
    @Override
    public void addCredentials(PreparedStatement statement, String username, String password) throws SQLException {
        statement.setString(1, username);
        statement.setString(2, password);
    }

    @Override
    public void addUser(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getEmail());
        statement.setDate(3, Date.valueOf(user.getDateOfRegistration()));
        statement.setString(4, user.getPhoneNumber());
        statement.setString(5, user.getLastName());
        statement.setBoolean(6, user.isGender());
        statement.setString(7, user.getUsername());
        statement.setString(8, user.getPassword());
        statement.setInt(9, user.getRole()
                                .getRoleId());//тут сделать +-1
    }

    @Override
    public void addId(PreparedStatement statement, long id) throws SQLException {
        statement.setLong(1, id);
    }

    @Override
    public void updateUser(PreparedStatement statement, User user) throws SQLException {
        statement.setNString(1, user.getFirstName());
        statement.setNString(2, user.getEmail());
        statement.setNString(3, user.getPhoneNumber());
        statement.setNString(4, user.getLastName());
        statement.setBoolean(5, user.isGender());
        statement.setNString(6, user.getPassword());
        statement.setLong(7, user.getId());
    }

    @Override
    public void changeDeletedStatus(PreparedStatement statement, boolean deletedStatus, long id) throws SQLException {
        statement.setBoolean(1, deletedStatus);
        statement.setLong(2, id);
    }

    @Override
    public void addBook(PreparedStatement statement, Book book) throws SQLException {
        try {
            statement.setBytes(1, book.getCover());
            statement.setNString(2, book.getTitle());
            statement.setNString(3, book.getAuthors());
            statement.setNString(4, book.getPublisher());
            statement.setDate(5, Date.valueOf(book.getPublishDate()));
            statement.setNString(6, book.getGenres());
            statement.setInt(7, book.getPageCount());
            statement.setNString(8, book.getISBN());
            statement.setNString(9, book.getDescription());
            statement.setInt(10, book.getTotalAmount());
            statement.setInt(11, book.getAvailableAmount());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateBook(PreparedStatement statement, Book book) throws SQLException {
        statement.setBytes(1, book.getCover());
        statement.setNString(2, book.getTitle());
        statement.setNString(3, book.getAuthors());
        statement.setNString(4, book.getPublisher());
        statement.setDate(5, Date.valueOf(book.getPublishDate()));
        statement.setNString(6, book.getGenres());
        statement.setInt(7, book.getPageCount());
        statement.setNString(8, book.getISBN());
        statement.setNString(9, book.getDescription());
        statement.setInt(10, book.getTotalAmount());
        statement.setInt(11, book.getAvailableAmount());
        statement.setLong(12, book.getId());
    }

    @Override
    public void addBorrowRecord(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException {
        statement.setLong(1, borrowRecord.getUserId());
        statement.setDate(2, Date.valueOf(borrowRecord.getBorrowDate()));
        statement.setDate(3, Date.valueOf(borrowRecord.getDueDate()));
        statement.setLong(4, borrowRecord.getBookId());
    }

    @Override
    public void updateBorrowRecordByAdmin(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException {
        statement.setInt(1, borrowRecord.getRecordStatus()
                                        .getBorrowRecordStatusId());
        statement.setNString(2, borrowRecord.getComment());
        statement.setLong(3, borrowRecord.getId());
    }

    @Override
    public void updateBorrowRecordByUser(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException {
        statement.setDate(1, Date.valueOf(borrowRecord.getReturnDate()));
        statement.setLong(2, borrowRecord.getId());
    }

    @Override
    public void addSearchParameters(PreparedStatement statement, String title, String authors, String genres, String description) throws SQLException {
        statement.setNString(1, title);
        statement.setNString(2, authors);
        statement.setNString(3, genres);
        statement.setNString(4, description);
    }

    @Override
    public void addPaginationParameters(PreparedStatement statement, int start, int recordsPerPage) throws SQLException {
        statement.setInt(1, start);
        statement.setInt(2, recordsPerPage);
    }

    @Override
    public void addISBN(PreparedStatement statement, String ISBN) throws SQLException {
        statement.setNString(1, ISBN);
    }

    @Override
    public void updateBookAvailableAmount(PreparedStatement statement, long bookId, int availableAmount) throws SQLException {
        statement.setInt(1, availableAmount);
        statement.setLong(2, bookId);
    }

    @Override
    public void addPaginationParameters(PreparedStatement statement, long userId, int start, int recordsPerPage) throws SQLException {
        statement.setLong(1, userId);
        statement.setInt(2, start);
        statement.setInt(3, recordsPerPage);
    }

    @Override
    public void addRemindDate(PreparedStatement statement, LocalDate dueDate) throws SQLException {
        statement.setDate(1, Date.valueOf(dueDate));
    }
}
