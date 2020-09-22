package by.itechart.library.service.util;

import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ServiceException;

public interface BookValidation {


    public boolean validateAdd(Book book) throws ServiceException;



}
