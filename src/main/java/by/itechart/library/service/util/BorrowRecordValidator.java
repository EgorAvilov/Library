package by.itechart.library.service.util;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.exception.ServiceException;

public interface BorrowRecordValidator {

    public boolean validateAdd(BorrowRecord borrowRecord) throws ServiceException;


}
