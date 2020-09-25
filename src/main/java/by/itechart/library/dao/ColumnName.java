package by.itechart.library.dao;

public class ColumnName {


    /**
     * column titles for table 'users'
     */
    public static final String USER_ID = "id";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_DATE_OF_REGISTRATION = "date_of_registration";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_GENDER = "gender";
    public static final String USER_DELETED_STATUS = "deleted_status";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE_ID = "role_id";

    /**
     * column titles for table 'books'
     */
    public static final String BOOK_ID = "id";
    public static final String BOOK_COVER = "cover";
    public static final String BOOK_TITLE = "title";
    public static final String BOOK_AUTHORS = "authors";
    public static final String BOOK_PUBLISHER = "publisher";
    public static final String BOOK_PUBLISH_DATE = "publish_date";
    public static final String BOOK_GENRES = "genres";
    public static final String BOOK_PAGE_COUNT = "page_count";
    public static final String BOOK_ISBN = "isbn";
    public static final String BOOK_DESCRIPTION = "description";
    public static final String BOOK_TOTAL_AMOUNT = "total_amount";
    public static final String BOOK_STATUS = "status";
    public static final String BOOK_AVAILABLE_AMOUNT = "available_amount";
    public static final String BOOK_DELETED_STATUS = "deleted_status";
    /**
     * column titles for table 'borrow_records'
     */
    public static final String BORROW_RECORD_ID = "id";
    public static final String BORROW_RECORD_USER_ID = "user_id";
    public static final String BORROW_RECORD_BORROW_DATE = "borrow_date";
    public static final String BORROW_RECORD_DUE_DATE = "due_date";
    public static final String BORROW_RECORD_RETURN_DATE = "return_date";
    public static final String BORROW_RECORD_BOOK_ID = "book_id";
    public static final String BORROW_RECORD_STATUS_ID = "status_id";
    public static final String BORROW_RECORD_COMMENT = "comment";
    /**
     * column titles for table 'roles'
     */
    public static final String ROLES_ID = "id";
    public static final String ROLES_ROLE = "role";


}
