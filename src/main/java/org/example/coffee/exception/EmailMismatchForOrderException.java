package org.example.coffee.exception;

public class EmailMismatchForOrderException extends RuntimeException{
    public EmailMismatchForOrderException() {
    }

    public EmailMismatchForOrderException(String message) {
        super(message);
    }

    public EmailMismatchForOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailMismatchForOrderException(Throwable cause) {
        super(cause);
    }

    public EmailMismatchForOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
