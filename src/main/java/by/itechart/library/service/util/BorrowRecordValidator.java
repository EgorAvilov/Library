package by.itechart.library.service.util;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;

public interface BorrowRecordValidator {

    public boolean validateAdd(BorrowRecord borrowRecord) throws ValidatorException;

    public boolean validateUpdateByUser(BorrowRecord borrowRecord) throws ValidatorException;

    public boolean validateUpdateByAdmin(BorrowRecord borrowRecord) throws ValidatorException;
    public boolean validateStatus(int borrowRecordStatusId) throws ValidatorException;
}
