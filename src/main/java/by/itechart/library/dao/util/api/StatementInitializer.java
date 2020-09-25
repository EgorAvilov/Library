package by.itechart.library.dao.util.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public interface StatementInitializer {
    public void addCredentials(PreparedStatement statement, String username, String password) throws SQLException;

    public void addUser(PreparedStatement statement, User user) throws SQLException;

    public void updateUser(PreparedStatement statement, User user) throws SQLException;

    public void changeDeletedStatus(PreparedStatement statement, boolean deletedStatus, long id) throws SQLException;

    public void addBook(PreparedStatement statement, Book book) throws SQLException;

    public void addId(PreparedStatement statement, long id) throws SQLException;

    public void updateBook(PreparedStatement statement, Book book) throws SQLException;

    public void addBorrowRecord(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

    public void updateBorrowRecordByAdmin(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

    public void updateBorrowRecordByUser(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

    public void addSearchParameters(PreparedStatement statement, String title, String authors, String genres, String description) throws SQLException;

    public void addPaginationParameters(PreparedStatement statement, int start, int recordsPerPage) throws SQLException;

    public void addISBN(PreparedStatement statement, String ISBN) throws SQLException;

    public void updateBookAvailableAmount(PreparedStatement statement, long bookId, int availableAmount) throws SQLException;

    public void addPaginationParameters(PreparedStatement statement, long userId, int start, int recordsPerPage) throws SQLException;

    public void addRemindDate(PreparedStatement statement, LocalDate dueDate) throws SQLException;
}
