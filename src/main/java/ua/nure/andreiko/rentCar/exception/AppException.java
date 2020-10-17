package ua.nure.andreiko.rentCar.exception;

/**
 * An exception that provides information on an application error.
 *
 * @author E.Andreiko
 */
public class AppException extends Exception {

    public AppException() {
        super();
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
