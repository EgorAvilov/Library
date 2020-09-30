package by.itechart.library.service.util.impl;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.BorrowRecordValidator;

import java.time.LocalDate;

public class BorrowRecordValidatorImpl implements BorrowRecordValidator {
    @Override
    public boolean validateAdd(BorrowRecord borrowRecord) throws ValidatorException {
        return validateDueDate(borrowRecord.getDueDate());
    }

    @Override
    public boolean validateUpdateByUser(BorrowRecord borrowRecord) throws ValidatorException {
        return validateReturnDate(borrowRecord.getReturnDate());
    }

    @Override
    public boolean validateUpdateByAdmin(BorrowRecord borrowRecord) throws ValidatorException {
        return validateStatusIsPreset(borrowRecord.getRecordStatus());
    }

    @Override
    public boolean validateStatusIsPreset(BorrowRecordStatus borrowRecordStatus) throws ValidatorException {
        // BorrowRecordStatus borrowRecordStatus = BorrowRecordStatus.values()[borrowRecordStatusId - 1];
        if (borrowRecordStatus == BorrowRecordStatus.RETURNED
                || borrowRecordStatus == BorrowRecordStatus.RETURNED_AND_DAMAGED
                || borrowRecordStatus == BorrowRecordStatus.LOST) {
            return true;
        }
        throw new ValidatorException("Borrow Record Status is wrong");
    }


    @Override
    public boolean validateStatus(BorrowRecordStatus borrowRecordStatus) {
        //BorrowRecordStatus borrowRecordStatus = BorrowRecordStatus.values()[borrowRecordStatusId - 1];
        return borrowRecordStatus == BorrowRecordStatus.RETURNED
                || borrowRecordStatus == BorrowRecordStatus.RETURNED_AND_DAMAGED;
    }

    @Override
    public boolean validateDueDate(LocalDate dueDate) throws ValidatorException {
        if (dueDate == null) {
            throw new ValidatorException("Due date cant be empty");
        }
        if (dueDate.isBefore(LocalDate.now())) {
            throw new ValidatorException("Due date cant be in past");
        }
        return true;
    }

    @Override
    public boolean validateReturnDate(LocalDate returnDate) throws ValidatorException {
        if (returnDate == null) {
            throw new ValidatorException("Return date cant be empty");
        }
        if (returnDate.isBefore(LocalDate.now()) || returnDate.equals(LocalDate.now())) {
            throw new ValidatorException("Return date cant be in past or equals today's date");
        }
        return true;
    }
}
