package by.itechart.library.controller.util.api;

public interface PathCreator {
    String getError();

    String getSignIn();

    String getMainPage();

    String getSignUp();

    String getBooksPage();

    String getBookPage(String contextPath, long bookId);

    String getUsersPage();

    String getUserPage();

    String getBorrowRecordsPage();

    String getBorrowRecordPage(String contextPath, long borrowRecordId);

    String getEditBook();

    String getEditBorrowRecord();

    String getForwardMainPage(String contextPath);

    String getForwardUsersPage(String contextPath);

    String getEditProfile();
}
