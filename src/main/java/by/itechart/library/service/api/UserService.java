package by.itechart.library.service.api;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    public void addBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

    public void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

    public List<BorrowRecord> getAllBorrowRecords(long userId, int currentPage, int recordsPerPage) throws ServiceException;

    public int getNumberOfBorrowRecordRows(long userId) throws ServiceException;
}
