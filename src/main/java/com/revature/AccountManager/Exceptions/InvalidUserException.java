package com.revature.AccountManager.Exceptions;
public class InvalidUserException extends Exception {

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserException(String message) {
        super(message);
    }

    public InvalidUserException(Throwable cause) {
        super(cause);
    }


}