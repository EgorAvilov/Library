package by.itechart.library.service.util;

import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.exception.ValidatorException;

public interface UserValidator {

    public boolean validateAdd(User user) throws ValidatorException;

    public boolean validateUpdate(User user) throws ValidatorException;

    boolean validateUsername(String username) throws ValidatorException;

    boolean validatePhoneNumber(String phoneNumber) throws ValidatorException;

    boolean validateEmail(String email) throws ValidatorException;

    boolean validateFirstName(String firstName) throws ValidatorException;

    boolean validateLastName(String lastName) throws ValidatorException;

    boolean validatePassword(String password) throws ValidatorException;
}
