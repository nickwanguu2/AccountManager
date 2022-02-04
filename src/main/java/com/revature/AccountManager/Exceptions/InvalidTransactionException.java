package com.revature.AccountManager.Exceptions;
public class InvalidTransactionException extends Exception {

    public InvalidTransactionException() {
        super();
    }

    public InvalidTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTransactionException(String message) {
        super(message);
    }

    public InvalidTransactionException(Throwable cause) {
        super(cause);
    }


}