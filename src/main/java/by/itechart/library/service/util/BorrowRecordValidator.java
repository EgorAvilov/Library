package by.itechart.library.service.util;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;

import java.time.LocalDate;

public interface BorrowRecordValidator {

    public boolean validateAdd(BorrowRecord borrowRecord) throws ValidatorException;

    public boolean validateUpdateByUser(BorrowRecord borrowRecord) throws ValidatorException;

    public boolean validateUpdateByAdmin(BorrowRecord borrowRecord) throws ValidatorException;

    boolean validateStatusIsPreset( BorrowRecordStatus borrowRecordStatus) throws ValidatorException;

    public boolean validateStatus( BorrowRecordStatus borrowRecordStatus) throws ValidatorException;

    boolean validateDueDate(LocalDate dueDate) throws ValidatorException;

    boolean validateReturnDate(LocalDate returnDate) throws ValidatorException;
}
