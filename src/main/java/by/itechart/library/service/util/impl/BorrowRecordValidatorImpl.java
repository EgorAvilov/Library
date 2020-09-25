package by.itechart.library.service.util.impl;

import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.util.BorrowRecordValidator;

import java.time.LocalDate;

public class BorrowRecordValidatorImpl implements BorrowRecordValidator {
    @Override
    public boolean validateAdd(BorrowRecord borrowRecord) throws ServiceException {
        return validateDueDate(borrowRecord.getDueDate());
    }

    private boolean validateDueDate(LocalDate dueDate) throws ServiceException {
        if(dueDate.isBefore(LocalDate.now())){
            throw new ServiceException("Due date cant be in past");
        }
        return true;
    }





}
