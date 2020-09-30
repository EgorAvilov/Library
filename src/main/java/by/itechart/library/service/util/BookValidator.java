package by.itechart.library.service.util;

import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ValidatorException;

import java.time.LocalDate;

public interface BookValidator {

    boolean validateAdd(Book book) throws ValidatorException;

    boolean validateUpdate(Book book) throws ValidatorException;

    boolean validateTitle(String title) throws ValidatorException;

    boolean validateAuthors(String authors) throws ValidatorException;

    boolean validatePublisher(String publisher) throws ValidatorException;

    boolean validatePublishDate(LocalDate publishDate) throws ValidatorException;

    boolean validateGenres(String genres) throws ValidatorException;

    boolean validateTotalAmount(int totalAmount) throws ValidatorException;

    boolean validatePageCount(int pageCount) throws ValidatorException;

    boolean validateISBNtoAdd(String ISBN) throws ValidatorException;

    boolean validateISBNtoUpdate(String ISBN, long id) throws ValidatorException;
}
