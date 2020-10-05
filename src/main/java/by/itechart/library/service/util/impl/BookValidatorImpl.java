package by.itechart.library.service.util.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.BookValidator;
import lombok.extern.log4j.Log4j;

import java.time.LocalDate;

@Log4j
public class BookValidatorImpl implements BookValidator {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();

    @Override
    public boolean validateAdd(Book book) throws ValidatorException {
        return validateTitle(book.getTitle())
                && validateAuthors(book.getAuthors())
                && validatePublisher(book.getPublisher())
                && validatePublishDate(book.getPublishDate())
                && validateGenres(book.getGenres())
                && validateTotalAmount(book.getTotalAmount())
                && validatePageCount(book.getPageCount())
                && validateISBNtoAdd(book.getISBN());
    }

    @Override
    public boolean validateUpdate(Book book) throws ValidatorException {
        return validateTitle(book.getTitle())
                && validateAuthors(book.getAuthors())
                && validatePublisher(book.getPublisher())
                && validatePublishDate(book.getPublishDate())
                && validateGenres(book.getGenres())
                && validateTotalAmount(book.getTotalAmount())
                && validatePageCount(book.getPageCount())
                && validateISBNtoUpdate(book.getISBN(), book.getId());
    }

    @Override
    public boolean validateTitle(String title) throws ValidatorException {
        if (title == null || title.isEmpty()) {
            throw new ValidatorException("message.empty_title");
        }
        if (!Character.isUpperCase(title.charAt(0)) && Character.isLetter(title.charAt(0))) {
            throw new ValidatorException("message.title_uppercase");
        }
        return true;
    }

    @Override
    public boolean validateAuthors(String authors) throws ValidatorException {
        if (authors == null || authors.isEmpty()) {
            throw new ValidatorException("message.empty_authors");
        }
        String[] authorsList = authors.split("\\s*(\\s|,|!|\\.)\\s*");
        for (String author : authorsList) {

            if (!Character.isUpperCase(author.charAt(0)) && Character.isLetter(author.charAt(0))) {
                throw new ValidatorException("message.authors_uppercase");
            }
        }
        return true;
    }

    @Override
    public boolean validatePublisher(String publisher) throws ValidatorException {
        if (publisher == null || publisher.isEmpty()) {
            throw new ValidatorException("message.empty_publisher");
        }
        if (!Character.isUpperCase(publisher.charAt(0)) && Character.isLetter(publisher.charAt(0))) {
            throw new ValidatorException("message.publisher_uppercase");
        }
        return true;
    }

    @Override
    public boolean validatePublishDate(LocalDate publishDate) throws ValidatorException {
        if (publishDate == null) {
            throw new ValidatorException("message.empty_publishDate");
        }
        if (publishDate.isAfter(LocalDate.now())) {
            throw new ValidatorException("message.future_date");
        }
        return true;
    }

    @Override
    public boolean validateGenres(String genres) throws ValidatorException {
        if (genres == null || genres.isEmpty()) {
            throw new ValidatorException("message.empty_genres");
        }
        return true;
    }

    @Override
    public boolean validateTotalAmount(int totalAmount) throws ValidatorException {
        if (totalAmount <= 0) {
            throw new ValidatorException("message.minus_totalAmount");
        }
        return true;
    }

    @Override
    public boolean validatePageCount(int pageCount) throws ValidatorException {
        if (pageCount <= 0) {
            throw new ValidatorException("message.minus_pageCount");
        }
        return true;
    }

    @Override
    public boolean validateISBNtoAdd(String ISBN) throws ValidatorException {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new ValidatorException("message.empty_ISBN");
        }
        try {
            bookDAO.checkISBNtoAdd(ISBN);
        } catch (DAOException e) {
            log.error(e);
            throw new ValidatorException(e);
        }
        return true;
    }
    @Override
    public boolean validateISBNtoUpdate(String ISBN, long id) throws ValidatorException {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new ValidatorException("message.empty_ISBN");
        }
        try {
            bookDAO.checkISBNtoUpdate(ISBN, id);
        } catch (DAOException e) {
            log.error(e);
            throw new ValidatorException(e);
        }
        return true;
    }
}
