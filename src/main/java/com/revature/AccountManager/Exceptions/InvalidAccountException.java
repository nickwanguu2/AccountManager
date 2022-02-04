package com.revature.AccountManager.Exceptions;

public class InvalidAccountException extends Exception {
    public InvalidAccountException() {
        super();
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(Throwable cause) {
        super(cause);
    }
}
