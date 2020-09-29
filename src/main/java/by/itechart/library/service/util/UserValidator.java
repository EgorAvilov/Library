package by.itechart.library.service.util;

import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;

public interface UserValidator {

    public boolean validateAdd(User user) throws ValidatorException;

    public boolean validateUpdate(User user) throws ValidatorException;

    public boolean validateUserDeletedStatus(Boolean deletedStatus) throws ValidatorException;


}
