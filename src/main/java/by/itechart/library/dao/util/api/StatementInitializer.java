package by.itechart.library.dao.util.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public interface StatementInitializer {
     void addCredentials(PreparedStatement statement, String username, String password) throws SQLException;

     void addUser(PreparedStatement statement, User user) throws SQLException;

     void updateUser(PreparedStatement statement, User user) throws SQLException;

     void changeDeletedStatus(PreparedStatement statement, long id) throws SQLException;

     void addBook(PreparedStatement statement, Book book) throws SQLException;

     void addId(PreparedStatement statement, long id) throws SQLException;

     void updateBook(PreparedStatement statement, Book book) throws SQLException;

     void addBorrowRecord(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

     void updateBorrowRecordByAdmin(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

     void updateBorrowRecordByUser(PreparedStatement statement, BorrowRecord borrowRecord) throws SQLException;

     void addSearchParameters(PreparedStatement statement, String searchParameter) throws SQLException;

     void addPaginationParameters(PreparedStatement statement, int start, int recordsPerPage) throws SQLException;

     void addISBNToUpdate(PreparedStatement statement, String ISBN, long id) throws SQLException;

     void addISBNToAdd(PreparedStatement statement, String ISBN) throws SQLException;

     void addEmailToAdd(PreparedStatement statement, String email) throws SQLException;

     void addUsernameToAdd(PreparedStatement statement, String username) throws SQLException;

     void updateBookAvailableAmount(PreparedStatement statement, long bookId, int availableAmount) throws SQLException;

     void addPaginationParameters(PreparedStatement statement, long userId, int start, int recordsPerPage) throws SQLException;

     void addRemindDate(PreparedStatement statement, LocalDate dueDate) throws SQLException;

     void changeUserRole(PreparedStatement statement, long userId) throws SQLException;
}
