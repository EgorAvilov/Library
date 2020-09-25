package by.itechart.library.dao.util.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.dto.EmailSenderDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultCreator {
    public Book getNextBook(ResultSet resultSet) throws SQLException;

    public BorrowRecord getNextBorrowRecord(ResultSet resultSet) throws SQLException;

    public User getNextUser(ResultSet resultSet) throws SQLException;

    public int getNextBookAvailableAmount(ResultSet resultSet) throws SQLException;

    public EmailSenderDto getNextEmailSender(ResultSet resultSet) throws SQLException;

}
