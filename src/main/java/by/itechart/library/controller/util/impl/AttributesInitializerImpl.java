package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.api.AttributesInitializer;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AttributesInitializerImpl implements AttributesInitializer {
    @Override
    public void setRequestAttributesBorrowRecords(HttpServletRequest request, List<BorrowRecord> borrowRecords) {
        request.setAttribute(ParameterName.BORROW_RECORDS, borrowRecords);
    }

    @Override
    public void setRequestAttributesBooks(HttpServletRequest request, List<Book> books) {
        request.setAttribute(ParameterName.BOOKS, books);

    }

    @Override
    public void setSessionAttributesUser(HttpSession session, User user) {
        session.setAttribute(ParameterName.USER, user);
    }

    @Override
    public void setRequestAttributesUsers(HttpServletRequest request, List<User> users) {
       // request.setAttribute(ParameterName.USERS, users);
    }

    @Override
    public void setRequestAttributesUser(HttpServletRequest request, User user) {
        request.setAttribute(ParameterName.USER, user);
    }
}
