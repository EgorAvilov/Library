package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.util.api.PathCreator;
import lombok.Getter;

@Getter
public class PathCreatorImpl implements PathCreator {
    private final static String BORROW_RECORD = "borrow-record";
    private final static String ERROR = "error";
    private final static String SIGN_IN = "sign-in";
    private final static String SIGN_UP = "sign-up";
    private final static String USER_PAGE = "user-page";
    private final static String USERS_PAGE = "users-page";
    private final static String MAIN_PAGE = "main-page";
    private final static String EDIT_BOOK = "add-book";
    private final static String EDIT_BORROW_RECORD = "edit-borrow-record";
    private final static String EDIT_PROFILE = "edit-profile";
    private final static String GET_BOOKS_PAGE = "/controller?command=view-all-books";
    private final static String GET_USERS_PAGE = "/controller?command=view-all-users";
    private final static String NO_ACCESS = "no-access";
    private final static String GET_BOOK_PAGE = "/controller?command=forward-edit-book";
    private final static String CONTROLLER_BOOK_ID = "/controller?bookId=";
    private final static String COMMAND_VIEW_BOOk = "&command=view-book";
    private final static String BOOK_PAGE = "book-page";
    private final static String BORROW_RECORDS = "borrow-records";
    private final static String FORWARD_VIEW_ALL_BORROW_RECORD_USER = "/controller?command=view-all-borrow-records-by-user";
    private final static String FORWARD_VIEW_ALL_BORROW_RECORD_ADMIN = "/controller?command=view-all-borrow-records-by-admin";

    @Override
    public String getBorrowRecord() {
        return BORROW_RECORD;
    }

    @Override
    public String getBookPage() {
        return BOOK_PAGE;
    }

    @Override
    public String getNoAccess() {
        return NO_ACCESS;
    }

    @Override
    public String getBooksPage() {
        return MAIN_PAGE;
    }

    @Override
    public String getForwardMainPage(String contextPath) {
        return contextPath + GET_BOOKS_PAGE;
    }

    @Override
    public String getForwardUsersPage(String contextPath) {
        return contextPath + GET_USERS_PAGE;
    }

    @Override
    public String getError() {
        return ERROR;
    }

    @Override
    public String getSignIn() {
        return SIGN_IN;
    }

    @Override
    public String getMainPage() {
        return MAIN_PAGE;
    }

    @Override
    public String getBookPage(String contextPath, long bookId) {
        return contextPath + CONTROLLER_BOOK_ID + bookId + COMMAND_VIEW_BOOk;
    }

    @Override
    public String getUsersPage() {
        return USERS_PAGE;
    }

    @Override
    public String getUserPage() {
        return USER_PAGE;
    }

    @Override
    public String getBorrowRecordsPage() {
        return BORROW_RECORDS;
    }

    @Override
    public String getBorrowRecordPageUser(String contextPath) {
        return contextPath + FORWARD_VIEW_ALL_BORROW_RECORD_USER;
    }

    @Override
    public String getBorrowRecordPageAdmin(String contextPath) {
        return contextPath + FORWARD_VIEW_ALL_BORROW_RECORD_ADMIN;
    }

    @Override
    public String getEditBook() {
        return EDIT_BOOK;
    }

    @Override
    public String getEditBorrowRecord() {
        return EDIT_BORROW_RECORD;
    }

    @Override
    public String getEditProfile() {
        return EDIT_PROFILE;
    }
}
