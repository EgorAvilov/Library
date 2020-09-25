package by.itechart.library.dao.util.impl;

import by.itechart.library.dao.ColumnName;
import by.itechart.library.dao.util.api.ResultCreator;
import by.itechart.library.entity.*;
import by.itechart.library.service.dto.EmailSenderDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ResultCreatorImpl implements ResultCreator {

    @Override
    public Book getNextBook(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ColumnName.BOOK_ID);
        byte[] cover = resultSet.getBytes(ColumnName.BOOK_COVER);
        String title = resultSet.getNString(ColumnName.BOOK_TITLE);
        String authors = resultSet.getNString(ColumnName.BOOK_AUTHORS);
        String publisher = resultSet.getNString(ColumnName.BOOK_PUBLISHER);
        LocalDate publishDate = resultSet.getDate(ColumnName.BOOK_PUBLISH_DATE).toLocalDate();
        String genres = resultSet.getNString(ColumnName.BOOK_GENRES);
        int pageCount = resultSet.getInt(ColumnName.BOOK_PAGE_COUNT);
        String ISBN = resultSet.getNString(ColumnName.BOOK_ISBN);
        String description = resultSet.getNString(ColumnName.BOOK_DESCRIPTION);
        int totalAmount = resultSet.getInt(ColumnName.BOOK_TOTAL_AMOUNT);
        boolean status = resultSet.getBoolean(ColumnName.BOOK_STATUS);///
        boolean deletedStatus = resultSet.getBoolean(ColumnName.BOOK_DELETED_STATUS);
        int availableAmount = resultSet.getInt(ColumnName.BOOK_AVAILABLE_AMOUNT);

        Book book = new Book();

        book.setId(id);
        book.setCover(cover);
        book.setTitle(title);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setPublishDate(publishDate);
        book.setGenres(genres);
        book.setPageCount(pageCount);
        book.setISBN(ISBN);
        book.setDescription(description);
        book.setTotalAmount(totalAmount);
        book.setStatus(status);///
        book.setDeletedStatus(deletedStatus);
        book.setAvailableAmount(availableAmount);

        return book;
    }

    @Override
    public BorrowRecord getNextBorrowRecord(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ColumnName.BORROW_RECORD_ID);
        long userId = resultSet.getLong(ColumnName.BORROW_RECORD_USER_ID);
        LocalDate borrowDate = resultSet.getDate(ColumnName.BORROW_RECORD_BORROW_DATE).toLocalDate();
        LocalDate dueDate = resultSet.getDate(ColumnName.BORROW_RECORD_DUE_DATE).toLocalDate();
        LocalDate returnDate = resultSet.getDate(ColumnName.BORROW_RECORD_RETURN_DATE).toLocalDate();
        int statusId = resultSet.getInt(ColumnName.BORROW_RECORD_STATUS_ID);
        String comment = resultSet.getNString(ColumnName.BORROW_RECORD_COMMENT);
        long bookId = resultSet.getLong(ColumnName.BORROW_RECORD_BOOK_ID);

        BorrowRecord borrowRecord = new BorrowRecord();

        borrowRecord.setId(id);
        borrowRecord.setUserId(userId);
        borrowRecord.setBorrowDate(borrowDate);
        borrowRecord.setDueDate(dueDate);
        borrowRecord.setReturnDate(returnDate);
        //borrowRecord.setStatusId(statusId);
        borrowRecord.setRecordStatus(BorrowRecordStatus.values()[statusId-1]);
        borrowRecord.setComment(comment);
        borrowRecord.setBookId(bookId);

        return borrowRecord;
    }

    @Override
    public User getNextUser(ResultSet resultSet) throws SQLException {

        long id = resultSet.getLong(ColumnName.USER_ID);
        String firstName = resultSet.getNString(ColumnName.USER_FIRST_NAME);
        String lastName = resultSet.getNString(ColumnName.USER_LAST_NAME);
        boolean gender = resultSet.getBoolean(ColumnName.USER_GENDER);
        String email = resultSet.getNString(ColumnName.USER_EMAIL);
        LocalDate dateOfRegistration = resultSet.getDate(ColumnName.USER_DATE_OF_REGISTRATION).toLocalDate();
        String phoneNumber = resultSet.getNString(ColumnName.USER_PHONE_NUMBER);
        String username = resultSet.getNString(ColumnName.USER_USERNAME);
        String password = resultSet.getNString(ColumnName.USER_PASSWORD);
        int roleId = resultSet.getInt(ColumnName.USER_ROLE_ID);
        boolean deletedStatus = resultSet.getBoolean(ColumnName.USER_DELETED_STATUS);

        User user = new User();

        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setEmail(email);
        user.setDateOfRegistration(dateOfRegistration);
        user.setPhoneNumber(phoneNumber);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(Role.values()[roleId-1]);
        user.setDeletedStatus(deletedStatus);

        return user;
    }

    @Override
    public int getNextBookAvailableAmount(ResultSet resultSet) throws SQLException {
        return resultSet.getInt(ColumnName.BOOK_AVAILABLE_AMOUNT);
    }

    @Override
    public EmailSenderDto getNextEmailSender(ResultSet resultSet) throws SQLException {





        return null;
    }
}
