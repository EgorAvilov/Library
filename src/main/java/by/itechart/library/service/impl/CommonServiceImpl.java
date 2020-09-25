package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.UtilFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.util.UserValidator;
import by.itechart.library.service.util.impl.UserValidatorImpl;
import lombok.extern.log4j.Log4j;

import java.util.List;
@Log4j
public class CommonServiceImpl implements CommonService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UtilFactory utilFactory=UtilFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();
    private BorrowRecordDAO borrowRecordDAO = daoFactory.getBorrowRecordDAO();
    private UserDAO userDAO = daoFactory.getUserDAO();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private UserValidator userValidation = utilFactory.getUserValidator();

    @Override
    public User signIn(String username, String password) throws ServiceException {
        User user;
        try {
            user = userDAO.getUser(username, password);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void signUp(User user) throws ServiceException {
        userValidation.validateAdd(user);
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User getProfile(long userId) throws ServiceException {
        User user;
        try {
            user = userDAO.getUser(userId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void updateProfile(User user) throws ServiceException {
        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getAllBooks(int currentPage, int recordsPerPage) throws ServiceException {
        List<Book> books;
        try {
            books = bookDAO.getAllBooks(currentPage,recordsPerPage);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public Book getBook(long bookId) throws ServiceException {
        Book book;
        try {
            book = bookDAO.getBook(bookId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return book;
    }

    @Override
    public BorrowRecord getBorrowRecord(long borrowRecordId) throws ServiceException {
        BorrowRecord borrowRecord;
        try {
            borrowRecord = borrowRecordDAO.getBorrowRecord(borrowRecordId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return borrowRecord;
    }

    @Override
    public List<Book> searchBooks(String title, String authors, String genres, String description) throws ServiceException {
        List<Book> books;
        try {
            books = bookDAO.searchBooks(title,authors,genres,description);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public int getNumberOfBookRows() throws ServiceException {
        int numberOfRows=0;
        try {
            numberOfRows=bookDAO.getNumberOfRows();
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return numberOfRows;
    }
}
