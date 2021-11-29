package com.example.bookstore.exception;

public class BookWithNameAlreadyExistsException extends RuntimeException {

    public BookWithNameAlreadyExistsException(String message) {
        super(message);
    }

}
