package by.itechart.library.service.util.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.util.BookValidator;

import java.time.LocalDate;

public class BookValidatorImpl implements BookValidator {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();

    @Override
    public boolean validateAdd(Book book) throws ServiceException {
        return validateTitle(book.getTitle())
                && validateAuthors(book.getAuthors())
                && validatePublisher(book.getPublisher())
                && validatePublishDate(book.getPublishDate())
                && validateGenres(book.getGenres())
                && validateTotalAmount(book.getTotalAmount())
                && validatePageCount(book.getPageCount())
                && validateISBN(book.getISBN());
    }

    @Override
    public boolean validateUpdate(Book book) throws ServiceException {
        return validateTitle(book.getTitle())
                && validateAuthors(book.getAuthors())
                && validatePublisher(book.getPublisher())
                && validatePublishDate(book.getPublishDate())
                && validateGenres(book.getGenres())
                && validateTotalAmount(book.getTotalAmount())
                && validatePageCount(book.getPageCount())
                && validateISBN(book.getISBN());
    }

    private boolean validateTitle(String title) throws ServiceException {
        if (title.isEmpty()) {
            throw new ServiceException("Title cant be empty");
        }
        if (!Character.isUpperCase(title.charAt(0))) {
            throw new ServiceException("Title should starts with upper case");
        }
        return true;
    }

    private boolean validateAuthors(String authors) throws ServiceException {
        String[] authorsList = authors.split("\\s*(\\s|,|!|\\.)\\s*");
        for (String author : authorsList) {//сравнение на заглавную первую букву
            if (!Character.isUpperCase(author.charAt(0))) {
                throw new ServiceException("Authors should start with upper case");
            }
        }
        for (int i = 0; i < authorsList.length; i++) {
            for (int j = i + 1; j < authorsList.length; j++) {
                if (authorsList[i].equalsIgnoreCase(authorsList[j])) {
                    throw new ServiceException("Authors should be unique");

                }
            }
        }
        return true;
    }

    private boolean validatePublisher(String publisher) throws ServiceException {
        if (publisher.isEmpty()) {
            throw new ServiceException("Publisher cant be empty");
        }
        if (!Character.isUpperCase(publisher.charAt(0))) {
            throw new ServiceException("Publisher should starts with upper case");
        }
        return true;
    }

    private boolean validatePublishDate(LocalDate publishDate) throws ServiceException {
        if (publishDate.isAfter(LocalDate.now())) {
            throw new ServiceException("Publish date cant be in future");
        }
        return true;
    }

    private boolean validateGenres(String genres) throws ServiceException {
        String[] genresList = genres.split("\\s*(\\s|,|!|\\.)\\s*");
        for (int i = 0; i < genresList.length; i++) {
            for (int j = i + 1; j < genresList.length; j++) {
                if (genresList[i].equalsIgnoreCase(genresList[j])) {
                    throw new ServiceException("Genres should be unique");

                }
            }
        }
        return true;
    }

    private boolean validateTotalAmount(int totalAmount) throws ServiceException {
        if (totalAmount <= 0) {
            throw new ServiceException("Total amount cant be <=0");
        }
        return true;
    }

    private boolean validatePageCount(int pageCount) throws ServiceException {
        if (pageCount <= 0) {
            throw new ServiceException("Page count cant be <=0");
        }
        return true;
    }

    private boolean validateISBN(String ISBN) throws ServiceException {
        try {
            bookDAO.checkISBN(ISBN);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
