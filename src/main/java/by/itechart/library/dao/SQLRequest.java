package by.itechart.library.dao;

public class SQLRequest {
    /**
     * запросы для таблицы Users
     */
    public static final String GET_USER_BY_CREDENTIALS = "SELECT * FROM users WHERE username=? AND password=? AND deleted_status<>1";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE  id=? AND deleted_status<>1";
    public static final String CREATE_USER = "INSERT INTO users (first_name, email, date_of_registration, phone_number, last_name,gender, username, password,role_id ) values(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET first_name=?, email=?, phone_number=?, last_name=?, gender=?, password=? WHERE id=?";
    public static final String CHANGE_USER_DELETED_STATUS = "UPDATE users SET deleted_status=? WHERE id=?";
    public static final String GET_ALL_USERS = "SELECT * FROM users  WHERE deleted_status<>1 LIMIT ?, ?";
    public static final String COUNT_ROWS_OF_USERS = "SELECT count(id) FROM users WHERE deleted_status<>1";
    public static final String GET_ALL_USERS_BY_USERNAME_TO_ADD = "SELECT * FROM users WHERE username = ? AND deleted_status<>1";
    public static final String GET_ALL_USERS_BY_EMAIL_TO_ADD = "SELECT * FROM users WHERE email = ? AND deleted_status<>1";

    /**
     * запросы для таблицы Book
     */
    public static final String GET_AVAILABLE_AMOUNT_OF_BOOKS_BY_ID = "SELECT available_amount FROM books WHERE id = ? AND deleted_status<>1";
    public static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE id = ? AND deleted_status<>1";
    public static final String GET_ALL_BOOKS = "SELECT * FROM books WHERE deleted_status<>1 LIMIT ?, ? ";
    public static final String GET_ALL_BOOKS_ORDER_BY = "SELECT * FROM books ORDER BY ?";
    public static final String CREATE_BOOK = "INSERT INTO books (cover, title, authors, publisher, publish_date, genres, page_count, isbn, description, total_amount, available_amount ) values(?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_BOOK = "UPDATE books SET cover=?, title=?, authors=?, publisher=?, publish_date=?,genres=?, page_count=?, isbn=?, description=?, total_amount=? available_amount=? WHERE id=?";
    public static final String CHANGE_BOOK_DELETED_STATUS = "UPDATE books SET deleted_status=? WHERE id=?";
    public static final String CHANGE_BOOK_AVAILABLE_AMOUNT = "UPDATE books SET available_amount=? WHERE id=?";
    public static final String SEARCH_BOOKS_BY_PARAMETERS = "SELECT * FROM books WHERE title LIKE '%?%' OR  authors LIKE '%?%' OR genres LIKE '%?%' OR description LIKE '%?%' AND deleted_status<>1";
    public static final String COUNT_ROWS_OF_BOOKS = "SELECT count(id) FROM books WHERE deleted_status<>1";
    public static final String GET_ALL_BOOKS_BY_ISBN_TO_UPDATE = "SELECT * FROM books WHERE isbn = ? AND id<>? AND deleted_status<>1";
    public static final String GET_ALL_BOOKS_BY_ISBN_TO_ADD = "SELECT * FROM books WHERE isbn = ? AND deleted_status<>1";


    /**
     * запросы для таблицы Borrow Record
     */
    public static final String GET_BORROW_RECORDS_BY_USER_ID = "SELECT * FROM borrow_records WHERE user_id = ? AND deleted_status<>1 LIMIT ?,? ";
    public static final String GET_ALL_BORROW_RECORDS = "SELECT * FROM borrow_records WHERE deleted_status<>1 LIMIT ?, ?";
    public static final String CREATE_BORROW_RECORD = "INSERT INTO borrow_records (user_id, borrow_date, due_date, book_id ) values(?,?,?,?)";
    public static final String UPDATE_BORROW_RECORD_BY_ADMIN = "UPDATE borrow_records SET  status_id=?, comment=? WHERE id=?";
    public static final String GET_BORROW_RECORD_BY_ID = "SELECT * FROM borrow_records WHERE id = ?  AND deleted_status<>1 LIMIT ?,?";
    public static final String UPDATE_BORROW_RECORD_BY_USER = "UPDATE borrow_records SET return_date=? WHERE id=?";
    public static final String COUNT_ROWS_OF_BORROW_RECORDS_BY_ADMIN = "SELECT count(id) FROM borrow_records WHERE deleted_status<>1";
    public static final String COUNT_ROWS_OF_BORROW_RECORDS_BY_USER = "SELECT count(id) FROM borrow_records WHERE user_id=? AND deleted_status<>1";

    public static final String GET_ALL_BORROW_RECORDS_FOR_REMINDS = "SELECT b.title, u.email, u.first_name FROM borrow_records br JOIN books b ON b.id=br.book_id JOIN users u ON br.user_id=u.id WHERE br.due_date=?";


}
