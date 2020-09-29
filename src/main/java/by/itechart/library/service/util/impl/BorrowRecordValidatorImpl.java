package by.itechart.library.service.util.impl;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.BorrowRecordStatus;
import by.itechart.library.service.exception.ServiceException;
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
        return validateStatusIsPreset(borrowRecord.getRecordStatus().getBorrowRecordStatusId());
    }
    private boolean validateStatusIsPreset(int borrowRecordStatusId) throws ValidatorException{
        BorrowRecordStatus borrowRecordStatus=BorrowRecordStatus.values()[borrowRecordStatusId-1];
        return borrowRecordStatus==BorrowRecordStatus.RETURNED
                || borrowRecordStatus==BorrowRecordStatus.RETURNED_AND_DAMAGED
                || borrowRecordStatus==BorrowRecordStatus.LOST;
    }


    @Override
    public boolean validateStatus(int borrowRecordStatusId) throws ValidatorException {
        BorrowRecordStatus borrowRecordStatus=BorrowRecordStatus.values()[borrowRecordStatusId-1];
        return borrowRecordStatus==BorrowRecordStatus.RETURNED;
    }

    private boolean validateDueDate(LocalDate dueDate) throws ValidatorException {
        if (dueDate.isBefore(LocalDate.now())) {
            throw new ValidatorException("Due date cant be in past");
        }
        return true;
    }


    private boolean validateReturnDate(LocalDate returnDate) throws ValidatorException {
        if (returnDate.isBefore(LocalDate.now()) || returnDate.equals(LocalDate.now())) {
            throw new ValidatorException("Return date cant be in past on equals today's date");
        }
        return true;
    }
}
