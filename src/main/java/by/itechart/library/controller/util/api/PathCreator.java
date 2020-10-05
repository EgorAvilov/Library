package by.itechart.library.controller.util.api;

public interface PathCreator {
    String getBorrowRecord();

    String getBookPage();

    String getNoAccess();

    String getError();

    String getSignIn();

    String getMainPage();

    String getBooksPage();

    String getBookPage(String contextPath, long bookId);

    String getUsersPage();

    String getUserPage();

    String getBorrowRecordsPage();

    String getBorrowRecordPageUser(String contextPath);

    String getBorrowRecordPageAdmin(String contextPath);

    String getEditBook();

    String getEditBorrowRecord();

    String getForwardMainPage(String contextPath);

    String getForwardUsersPage(String contextPath);

    String getEditProfile();
}
