package by.itechart.library.dao.api;

import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.User;

import java.util.List;

public interface UserDAO {
     User getUser(String username, String password) throws DAOException;

     void addUser(User user) throws DAOException;

     void updateUser(User user) throws DAOException;

     User getUser(long userId) throws DAOException;

     void changeDeletedStatus(long userId) throws DAOException;

     void changeRole(long userId) throws DAOException;

     int getNumberOfRows() throws DAOException;

     List<User> getAll(int currentPage, int recordsPerPage) throws DAOException;

     boolean checkEmail(String email) throws DAOException;

     boolean checkUsername(String username) throws DAOException;
}
