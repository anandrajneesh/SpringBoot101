package org.extras.exceptions;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public class AccessDbException extends ReadableException{
    public AccessDbException() {
    }

    public AccessDbException(String message) {
        super(message);
    }

    public AccessDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDbException(Throwable cause) {
        super(cause);
    }

    public AccessDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
