package com.revature.AccountManager.Exceptions;
public class IllegalTransactionException extends Exception {

    public IllegalTransactionException() {
        super();
    }

    public IllegalTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalTransactionException(String message) {
        super(message);
    }

    public IllegalTransactionException(Throwable cause) {
        super(cause);
    }


}