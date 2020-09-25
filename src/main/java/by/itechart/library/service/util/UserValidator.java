package by.itechart.library.service.util;

import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;

public interface UserValidator {

    public boolean validateAdd(User user) throws ServiceException;

    public boolean validateUpdate(User user) throws ServiceException;

}
