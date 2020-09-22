package by.itechart.library.controller.util.api;

import by.itechart.library.entity.Book;
import by.itechart.library.entity.BorrowRecord;
import by.itechart.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AttributesInitializer {
    public void setSessionAttributesUser(HttpSession session, User user);

    public void setRequestAttributesBorrowRecords(HttpServletRequest request, List<BorrowRecord> borrowRecords);

    public void setRequestAttributesBooks(HttpServletRequest request, List<Book> books);

    public void setRequestAttributesUsers(HttpServletRequest request, List<User> users);

    public void setRequestAttributesUser(HttpServletRequest request, User user);


}
