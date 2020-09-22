package by.itechart.library.dao.exception;

public class ConnectionPoolException extends Exception{

    private static final long serialVersionUID = -6123779693808575504L;

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
