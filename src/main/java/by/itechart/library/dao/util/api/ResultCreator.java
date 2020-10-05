package by.itechart.library.dao.util.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.dto.BorrowRecordDto;
import by.itechart.library.service.dto.EmailSenderDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultCreator {
    Book getNextBook(ResultSet resultSet) throws SQLException;

    BorrowRecord getNextBorrowRecord(ResultSet resultSet) throws SQLException;

    BorrowRecordDto getNextBorrowRecordDto(ResultSet resultSet) throws SQLException;

    User getNextUser(ResultSet resultSet) throws SQLException;

    int getNextBookAvailableAmount(ResultSet resultSet) throws SQLException;

    EmailSenderDto getNextEmailSender(ResultSet resultSet) throws SQLException;

}
