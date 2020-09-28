package by.itechart.library.controller.util.api;

public interface PathCreator {
    public String getError();
    public String getSignIn();
    public String getMainPage();

    public String getBooksPage();
    public String getBookPage(String contextPath, long bookId);
    public String getUsersPage();
    public String getUserPage();
    public String getBorrowRecordsPage();
    public String getBorrowRecordPage(String contextPath, long borrowRecordId);
    public String getEditBook();
    public String getForwardMainPage(String contextPath);

    public String getEditProfile();
}
