package by.itechart.library.service.api;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.dto.BorrowRecordDto;
import by.itechart.library.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    BorrowRecord getBorrowRecord(long borrowRecordId) throws ServiceException;

    void addBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;

    void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException;



    List<BorrowRecordDto> getAllBorrowRecords(long userId) throws ServiceException;

    int getNumberOfBorrowRecordRows(long userId) throws ServiceException;
}
