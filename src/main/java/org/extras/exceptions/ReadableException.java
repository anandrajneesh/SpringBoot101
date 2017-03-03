package org.extras.exceptions;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public class ReadableException extends Exception {

    public ReadableException() {
    }

    public ReadableException(String message) {
        super(message);
    }

    public ReadableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadableException(Throwable cause) {
        super(cause);
    }

    public ReadableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
