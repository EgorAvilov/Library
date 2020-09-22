package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.BorrowRecord;

import java.util.List;

public interface BorrowRecordDAO {

    public List<BorrowRecord> getAll(int currentPage, int recordsPerPage) throws DAOException;

    public void addBorrowRecord(BorrowRecord borrowRecord) throws DAOException;

    public List<BorrowRecord> getAllByUserId(long userId, int currentPage, int recordsPerPage) throws DAOException;

    public void updateBorrowRecordByAdmin(BorrowRecord borrowRecord) throws DAOException;

    public void updateBorrowRecordByUser(BorrowRecord borrowRecord) throws DAOException;

    public BorrowRecord getBorrowRecord(long id) throws DAOException;

    public int getNumberOfRowsByAdmin() throws DAOException;

    public int getNumberOfRowsByUser(long userId) throws DAOException;
}
