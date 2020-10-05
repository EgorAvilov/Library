package by.itechart.library.service.exception;

public class InvalidValuesException extends ServiceException {
    private static final long serialVersionUID = 6717405373605109410L;

    public InvalidValuesException(String message) {
        super(message);
    }

    public InvalidValuesException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValuesException(Throwable cause) {
        super(cause);
    }

    protected InvalidValuesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
