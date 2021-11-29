package com.example.bookstore.exception;

public class UserWithNameAlreadyExistsException extends RuntimeException {

    public UserWithNameAlreadyExistsException(String message) {
        super(message);
    }

}
