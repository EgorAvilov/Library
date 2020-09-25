package by.itechart.library.service.util;

import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;

public interface BookValidator {

    public boolean validateAdd(Book book) throws ValidatorException;

    public boolean validateUpdate(Book book) throws ValidatorException;

}
