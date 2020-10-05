package by.itechart.library.service.util.impl;

import by.itechart.library.dao.DAOFactory;
import by.itechart.library.dao.api.UserDAO;
import by.itechart.library.dao.exception.DAOException;
import by.itechart.library.entity.User;
import by.itechart.library.service.exception.ValidatorException;
import by.itechart.library.service.util.UserValidator;
import lombok.extern.log4j.Log4j;

@Log4j
public class UserValidatorImpl implements UserValidator {
    private static final String USERNAME_FORMAT_REGEX = "[A-Za-z0-9][A-Za-z0-9_-]{4,13}[A-Za-z0-9]";
    private static final String FIRST_NAME_FORMAT_REGEX = ".{2,30}";
    private static final String LAST_NAME_FORMAT_REGEX = ".{2,30}";
    private static final String EMAIL_FORMAT_REGEX = "[a-z][[a-z][0-9][-][_]]{3,15}[@][a-z]{2,10}[.][a-z]{2,4}";// ok egoravilov99@gmail.com
    private static final String PASSWORD_FORMAT_REGEX = "^(?=.*[0-9]).{8,15}$";//ok password1
    private static final String PHONE_NUMBER_FORMAT_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";// ok +375 (044) 792-0178"
    private DAOFactory daoFactory = DAOFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public boolean validateAdd(User user) throws ValidatorException {
        return validateUsername(user.getUsername())
                && validatePassword(user.getPassword())
                && validateEmail(user.getEmail())
                && validateFirstName(user.getFirstName())
                && validateLastName(user.getLastName())
                && validatePhoneNumber(user.getPhoneNumber());
    }

    @Override
    public boolean validateUpdate(User user) throws ValidatorException {
        return validatePassword(user.getPassword())
                && validateFirstName(user.getFirstName())
                && validateLastName(user.getLastName())
                && validatePhoneNumber(user.getPhoneNumber());
    }

    @Override
    public boolean validateUsername(String username) throws ValidatorException {
        if (username != null && !username.isEmpty()) {
            if (username.matches(USERNAME_FORMAT_REGEX)) {
                try {
                    userDAO.checkUsername(username);
                    return true;
                } catch (DAOException e) {
                    log.error(e);
                    throw new ValidatorException("User with such username already exists");
                }
            }

        }
        throw new ValidatorException("Username is invalid");
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) throws ValidatorException {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (phoneNumber.matches(PHONE_NUMBER_FORMAT_REGEX)) {
               return true;
            }
        }
        throw new ValidatorException("Phone number is invalid");
    }

    @Override
    public boolean validateEmail(String email) throws ValidatorException {
        if (email != null && !email.isEmpty()) {
            if (email.matches(EMAIL_FORMAT_REGEX)) {
                try {
                    userDAO.checkEmail(email);
                    return true;
                } catch (DAOException e) {
                    log.error(e);
                    throw new ValidatorException("User with such email already exists");
                }
            }
        }
        throw new ValidatorException("Email is invalid");
    }

    @Override
    public boolean validateFirstName(String firstName) throws ValidatorException {
        if (firstName != null && !firstName.isEmpty()) {
            if (firstName.matches(FIRST_NAME_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ValidatorException("First name is invalid");
    }

    @Override
    public boolean validateLastName(String lastName) throws ValidatorException {
        if (lastName != null && !lastName.isEmpty()) {
            if (lastName.matches(LAST_NAME_FORMAT_REGEX)) {
                return true;
            }

        }
        throw new ValidatorException("Last name is invalid");
    }

    @Override
    public boolean validatePassword(String password) throws ValidatorException {
        if (password != null && !password.isEmpty()) {
            if (password.matches(PASSWORD_FORMAT_REGEX)) {
                return true;
            }
        }
        throw new ValidatorException("Password is invalid");
    }
}
