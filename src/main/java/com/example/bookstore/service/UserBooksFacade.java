package com.example.bookstore.service;

import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.UserRequestDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.exception.UserNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBooksFacade {

    private final BookService bookService;
    private final UserService userService;

    public List<BookResponseDto> getUsersBook(String userId) {
        if (!userService.existsByUserId(userId)) {
            throw new UserNotFoundException(String.format("User with id '%s' not found", userId));
        }

        return bookService.getUsersBook(userId);
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        List<Book> books = bookService.findBooksByIds(userRequestDto.getBookIds());
        return userService.createUser(userRequestDto, books);
    }

}
