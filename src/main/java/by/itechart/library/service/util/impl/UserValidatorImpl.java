package by.itechart.library.service.util.impl;

import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ServiceException;
import by.itechart.library.service.util.UserValidator;

public class UserValidatorImpl implements UserValidator {

    private static final String USERNAME_FORMAT_REGEX = "[A-Za-z0-9][A-Za-z0-9_-]{4,13}[A-Za-z0-9]";
    private static final String FIRST_NAME_FORMAT_REGEX = ".{2,30}";
    private static final String LAST_NAME_FORMAT_REGEX = ".{2,30}";
    private static final String EMAIL_FORMAT_REGEX = "[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}";
    private static final String PASSWORD_FORMAT_REGEX = "^(?=.*[0-9]).{8,15}$";
    private static final String PHONE_NUMBER_FORMAT_REGEX = "(\\+*)\\d{11}";

    @Override
    public boolean validateAdd(User user) throws ServiceException {
        return validateUsername(user.getUsername())
                && validatePassword(user.getPassword())
                && validateEmail(user.getEmail())
                && validateFirstName(user.getFirstName())
                && validateLastName(user.getLastName())
                && validatePhoneNumber(user.getPhoneNumber());
    }

    @Override
    public boolean validateUpdate(User user) throws ServiceException {
        return validatePassword(user.getPassword())
                && validateFirstName(user.getFirstName())
                && validateLastName(user.getLastName())
                && validatePhoneNumber(user.getPhoneNumber());
    }

    private boolean validateUsername(String username) throws ServiceException {
        if (username != null && !username.isEmpty()) {
            if (username.matches(USERNAME_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ServiceException("Username is invalid");
    }

    private boolean validatePhoneNumber(String phoneNumber) throws ServiceException {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (phoneNumber.matches(PHONE_NUMBER_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ServiceException("Phone number is invalid");
    }

    private boolean validateEmail(String email) {
        return email.matches(EMAIL_FORMAT_REGEX);
    }

    private boolean validateFirstName(String firstName) throws ServiceException {
        if (firstName != null && !firstName.isEmpty()) {
            if (firstName.matches(FIRST_NAME_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ServiceException("First name is invalid");
    }

    private boolean validateLastName(String lastName) throws ServiceException {
        if (lastName != null && !lastName.isEmpty()) {
            if (lastName.matches(LAST_NAME_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ServiceException("Last name is invalid");
    }

    private boolean validatePassword(String password) throws ServiceException {
        if (password != null && !password.isEmpty()) {
            if (password.matches(PASSWORD_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ServiceException("Password is invalid");
    }
}