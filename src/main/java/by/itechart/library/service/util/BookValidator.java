package by.itechart.library.service.util;

import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ServiceException;

public interface BookValidator {

    public boolean validateAdd(Book book) throws ServiceException;

    public boolean validateUpdate(Book book) throws ServiceException;

}
