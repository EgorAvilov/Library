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

    //сделать првоерку на размер картинки
    @Override
    public boolean validateAdd(Book book) throws ValidatorException {
        return validateTitle(book.getTitle())
                && validateAuthors(book.getAuthors())
                && validatePublisher(book.getPublisher())
                && validatePublishDate(book.getPublishDate())
                && validateGenres(book.getGenres())
                && validateTotalAmount(book.getTotalAmount())
                && validatePageCount(book.getPageCount())
                && validateISBN(book.getISBN(), book.getId());
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
                && validateISBN(book.getISBN(), book.getId());
    }

    @Override
    public boolean validateTitle(String title) throws ValidatorException {
        if (title == null || title.isEmpty()) {
            throw new ValidatorException("Title cant be empty");
        }
        if (!Character.isUpperCase(title.charAt(0)) && Character.isLetter(title.charAt(0))) {
            throw new ValidatorException("Title should starts with upper case");
        }
        return true;
    }

    @Override
    public boolean validateAuthors(String authors) throws ValidatorException {
        if (authors == null || authors.isEmpty()) {
            throw new ValidatorException("Authors cant be empty");
        }
        String[] authorsList = authors.split("\\s*(\\s|,|!|\\.)\\s*");
        for (String author : authorsList) {

            if (!Character.isUpperCase(author.charAt(0)) && Character.isLetter(author.charAt(0))) {
                throw new ValidatorException("Authors should start with upper case");
            }
        }
        for (int i = 0; i < authorsList.length; i++) {
            for (int j = i + 1; j < authorsList.length; j++) {
                if (authorsList[i].equalsIgnoreCase(authorsList[j])) {
                    throw new ValidatorException("Authors should be unique");
                }
            }
        }
        return true;
    }

    @Override
    public boolean validatePublisher(String publisher) throws ValidatorException {
        if (publisher == null || publisher.isEmpty()) {
            throw new ValidatorException("Publisher cant be empty");
        }
        if (!Character.isUpperCase(publisher.charAt(0)) && Character.isLetter(publisher.charAt(0))) {
            throw new ValidatorException("Publisher should starts with upper case");
        }
        return true;
    }

    @Override
    public boolean validatePublishDate(LocalDate publishDate) throws ValidatorException {
        if (publishDate == null) {
            throw new ValidatorException("Publish date cant be empty");
        }
        if (publishDate.isAfter(LocalDate.now())) {
            throw new ValidatorException("Publish date cant be in future");
        }
        return true;
    }

    @Override
    public boolean validateGenres(String genres) throws ValidatorException {
        if (genres == null || genres.isEmpty()) {
            throw new ValidatorException("Genres cant be empty");
        }
        String[] genresList = genres.split("\\s*(\\s|,|!|\\.)\\s*");
        for (String genre : genresList) {
            if (!Character.isUpperCase(genre.charAt(0)) && Character.isLetter(genre.charAt(0))) {
                throw new ValidatorException("Genres should start with upper case");
            }
        }
        for (int i = 0; i < genresList.length; i++) {
            for (int j = i + 1; j < genresList.length; j++) {
                if (genresList[i].equalsIgnoreCase(genresList[j])) {
                    throw new ValidatorException("Genres should be unique");
                }
            }
        }
        return true;
    }

    @Override
    public boolean validateTotalAmount(int totalAmount) throws ValidatorException {
        if (totalAmount <= 0) {
            throw new ValidatorException("Total amount cant be <=0");
        }
        return true;
    }

    @Override
    public boolean validatePageCount(int pageCount) throws ValidatorException {
        if (pageCount <= 0) {
            throw new ValidatorException("Page count cant be <=0");
        }
        return true;
    }

    @Override
    public boolean validateISBN(String ISBN, long id) throws ValidatorException {
        if (ISBN == null || ISBN.isEmpty()) {
            throw new ValidatorException("ISBN cant be empty");
        }
        try {
            bookDAO.checkISBN(ISBN, id);
        } catch (DAOException e) {
            log.error(e);
            throw new ValidatorException(e);
        }
        return true;
    }
}
