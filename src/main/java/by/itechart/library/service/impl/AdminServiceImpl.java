package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;
import by.itechart.library.service.api.AdminService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class AdminServiceImpl implements AdminService {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();
    private BorrowRecordDAO borrowRecordDAO = daoFactory.getBorrowRecordDAO();
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public void addBook(Book book) throws ServiceException {
        try {
            bookDAO.addBook(book);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBook(Book book) throws ServiceException {
        try {
            bookDAO.updateBook(book);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }


    @Override
    public int changeBookDeletedStatus(long bookId) throws ServiceException {
        int result;
        try {
            result = bookDAO.changeDeletedStatus(bookId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public int changeUserDeletedStatus(long userId) throws ServiceException {
        int result;
        try {
            result = userDAO.changeDeletedStatus(userId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords(int currentPage, int recordsPerPage) throws ServiceException {
        List<BorrowRecord> borrowRecords;
        try {
            borrowRecords = borrowRecordDAO.getAll(currentPage, recordsPerPage);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return borrowRecords;
    }

    @Override
    public void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException {
        try {
            borrowRecordDAO.updateBorrowRecordByAdmin(borrowRecord);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllUsers(int currentPage, int recordsPerPage) throws ServiceException {
        List<User> users;
        try {
            users = userDAO.getAll(currentPage, recordsPerPage);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return users;
    }

    @Override
    public int getNumberOfBorrowRecordRows() throws ServiceException {
        int numberOfRows = 0;
        try {
            numberOfRows = borrowRecordDAO.getNumberOfRowsByAdmin();
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return numberOfRows;
    }

    @Override
    public int getNumberOfUserRows() throws ServiceException {
        int numberOfRows = 0;
        try {
            numberOfRows = userDAO.getNumberOfRows();
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return numberOfRows;
    }


}
