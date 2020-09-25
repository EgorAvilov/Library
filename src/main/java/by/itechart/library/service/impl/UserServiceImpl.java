package by.itechart.library.service.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.BookDAO;
import by.itechart.library.dao.api.BorrowRecordDAO;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import java.util.List;
@Log4j
public class UserServiceImpl implements UserService {
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private BookDAO bookDAO = daoFactory.getBookDAO();
    private BorrowRecordDAO borrowRecordDAO = daoFactory.getBorrowRecordDAO();
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) throws ServiceException {
        long bookId = borrowRecord.getBookId();
        try {
            bookDAO.takeBook(bookId);
            borrowRecordDAO.addBorrowRecord(borrowRecord);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateBorrowRecord(BorrowRecord borrowRecord) throws ServiceException {
        try {
            borrowRecordDAO.updateBorrowRecordByUser(borrowRecord);
        } catch (DAOException e) {//здесь вернуть книгу в availableAmount но проверить тсатус который дает админ
            log.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords(long userId, int currentPage, int recordsPerPage) throws ServiceException {
        List<BorrowRecord> borrowRecords;
        try {
            borrowRecords = borrowRecordDAO.getAllByUserId(userId, currentPage, recordsPerPage);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return borrowRecords;
    }

    @Override
    public int getNumberOfBorrowRecordRows(long userId) throws ServiceException {
        int numberOfRows = 0;
        try {
            numberOfRows = borrowRecordDAO.getNumberOfRowsByUser(userId);
        } catch (DAOException e) {
            log.error(e);
            throw new ServiceException(e);
        }
        return numberOfRows;
    }
}
