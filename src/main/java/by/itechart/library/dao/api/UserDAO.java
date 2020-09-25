package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.User;

import java.util.List;

public interface UserDAO {
    public User getUser(String username, String password) throws DAOException;

    public void addUser(User user) throws DAOException;

    public void updateUser(User user) throws DAOException;

    public User getUser(long userId) throws DAOException;

    public int changeDeletedStatus(long userId) throws DAOException;

    public int getNumberOfRows() throws DAOException;

    public List<User> getAll(int currentPage, int recordsPerPage) throws DAOException;
}
