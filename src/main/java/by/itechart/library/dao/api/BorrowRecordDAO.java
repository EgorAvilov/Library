package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.dto.EmailSenderDto;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRecordDAO {

    List<BorrowRecord> getAll(int currentPage, int recordsPerPage) throws DAOException;

    void addBorrowRecord(BorrowRecord borrowRecord) throws DAOException;

    List<BorrowRecord> getAllByUserId(long userId, int currentPage, int recordsPerPage) throws DAOException;

    void updateBorrowRecordByAdmin(BorrowRecord borrowRecord) throws DAOException;

    void updateBorrowRecordByUser(BorrowRecord borrowRecord) throws DAOException;

    BorrowRecord getBorrowRecord(long id) throws DAOException;

    int getNumberOfRowsByAdmin() throws DAOException;

    int getNumberOfRowsByUser(long userId) throws DAOException;

    List<EmailSenderDto> getAllBorrowRecordsForRemind(LocalDate remindDate) throws DAOException;
}
