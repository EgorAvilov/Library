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
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.UserValidator;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class CommonServiceImpl implements CommonService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UtilFactory utilFactory = UtilFactory.getInstance();
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
            if (user.isDeletedStatus()) {
                throw new ServiceException("Your account is deleted");
            }
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void signUp(User user) throws ServiceException {
        try {
            userValidation.validateAdd(user);
            userDAO.addUser(user);
        } catch (DAOException | ValidatorException e) {
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
            userValidation.validateUpdate(user);
            userDAO.updateUser(user);
        } catch (DAOException | ValidatorException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Book> getAllBooks(int currentPage, int recordsPerPage) throws ServiceException {
        List<Book> books;
        try {
            books = bookDAO.getAllBooks(currentPage, recordsPerPage);
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
    public List<Book> searchBooks(String searchParameter) throws ServiceException {
        List<Book> books;
        try {
            books = bookDAO.searchBooks(searchParameter);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public int getNumberOfBookRows() throws ServiceException {
        int numberOfRows = 0;
        try {
            numberOfRows = bookDAO.getNumberOfRows();
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return numberOfRows;
    }
}
