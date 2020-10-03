package by.itechart.library.service.util.impl;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.service.exception.ValidatorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.mockito.MockitoAnnotations.initMocks;

public class BorrowRecordValidatorTest {
    @InjectMocks
    BorrowRecordValidatorImpl borrowRecordValidator;

    BorrowRecord borrowRecord;

    @Before
    public void init() {
        initMocks(this);

        borrowRecord = new BorrowRecord();
    }

    @Test(expected = ValidatorException.class)
    public void validateAdd_isNull() throws ValidatorException {
        borrowRecordValidator.validateAdd(borrowRecord);
    }

    @Test
    public void validateAdd_validData() throws ValidatorException {
        borrowRecord.setDueDate(LocalDate.now()
                                         .plusDays(1));
        borrowRecordValidator.validateAdd(borrowRecord);
    }

    @Test(expected = ValidatorException.class)
    public void validateUpdateByUser_isNull() throws ValidatorException {
        borrowRecordValidator.validateUpdateByUser(borrowRecord);
    }

    @Test
    public void validateUpdateByUser_validData() throws ValidatorException {
        borrowRecord.setReturnDate(LocalDate.now()
                                            .plusDays(1));
        borrowRecordValidator.validateUpdateByUser(borrowRecord);
    }

    @Test(expected = ValidatorException.class)
    public void validateUpdateByAdmin_isNull() throws ValidatorException {
        borrowRecordValidator.validateUpdateByAdmin(borrowRecord);
    }

    @Test
    public void validateUpdateByAdmin_validData() throws ValidatorException {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecordValidator.validateUpdateByAdmin(borrowRecord);
    }

    @Test(expected = ValidatorException.class)
    public void validateStatusIsPreset_isNull() throws ValidatorException {
        borrowRecord.setRecordStatus(null);
        borrowRecordValidator.validateStatusIsPreset(borrowRecord.getRecordStatus());
    }

    @Test
    public void validateStatusIsPreset_validData() throws ValidatorException {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecordValidator.validateStatusIsPreset(borrowRecord.getRecordStatus());
    }

    @Test
    public void validateStatus_isNull() {
        borrowRecord.setRecordStatus(null);
        borrowRecordValidator.validateStatus(borrowRecord.getRecordStatus());
    }

    @Test
    public void validateStatus_validData() {
        borrowRecord.setRecordStatus(BorrowRecordStatus.RETURNED);
        borrowRecordValidator.validateStatus(borrowRecord.getRecordStatus());
    }

    @Test(expected = ValidatorException.class)
    public void validateDueDate_isNull() throws ValidatorException {
        borrowRecord.setDueDate(null);
        borrowRecordValidator.validateDueDate(borrowRecord.getDueDate());
    }

    @Test(expected = ValidatorException.class)
    public void validateDueDate_isBefore() throws ValidatorException {
        borrowRecord.setDueDate(LocalDate.now()
                                         .minusDays(1));
        borrowRecordValidator.validateDueDate(borrowRecord.getDueDate());
    }

    @Test
    public void validateDueDate_validData() throws ValidatorException {
        borrowRecord.setDueDate(LocalDate.now()
                                         .plusDays(1));
        borrowRecordValidator.validateDueDate(borrowRecord.getDueDate());
    }

    @Test(expected = ValidatorException.class)
    public void validateReturn_isNull() throws ValidatorException {
        borrowRecord.setReturnDate(null);
        borrowRecordValidator.validateReturnDate(borrowRecord.getReturnDate());
    }

    @Test(expected = ValidatorException.class)
    public void validateReturn_isBefore() throws ValidatorException {
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordValidator.validateReturnDate(borrowRecord.getReturnDate());
    }

    @Test
    public void validateReturn_validData() throws ValidatorException {
        borrowRecord.setReturnDate(LocalDate.now()
                                            .plusDays(1));
        borrowRecordValidator.validateReturnDate(borrowRecord.getReturnDate());
    }
}