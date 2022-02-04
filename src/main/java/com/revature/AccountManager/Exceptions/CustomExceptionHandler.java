package com.revature.AccountManager.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = InvalidUserException.class)
    public ResponseEntity<ErrorResponse> meth1(InvalidTransactionException invalidUserException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = invalidUserException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidAccountException.class)
    public ResponseEntity<ErrorResponse> meth2(InvalidAccountException invalidAccountException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = invalidAccountException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTransactionException.class)
    public ResponseEntity<ErrorResponse> meth3(InvalidTransactionException invalidTransactionException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = invalidTransactionException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = WrongPasswordException.class)
    public ResponseEntity<ErrorResponse> meth4(WrongPasswordException wrongPasswordException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = wrongPasswordException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalTransactionException.class)
    public ResponseEntity<ErrorResponse> meth5(IllegalTransactionException illegalTransactionException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = illegalTransactionException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> meth(Exception exception){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = exception.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}