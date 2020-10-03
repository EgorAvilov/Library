package by.itechart.library.service.api;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {
     void addBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

     void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

     List<BorrowRecord> getAllBorrowRecords(long userId, int currentPage, int recordsPerPage) throws ServiceException;

     int getNumberOfBorrowRecordRows(long userId) throws ServiceException;
}
