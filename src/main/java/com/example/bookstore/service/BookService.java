package com.example.bookstore.service;

import com.example.bookstore.dto.BookRequestDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateDto;
import com.example.bookstore.entities.Book;
import java.util.List;

public interface BookService {

    BookResponseDto createBook(BookRequestDto bookRequestDto);

    List<BookResponseDto> findAll(Integer from, Integer size);

    BookResponseDto findById(String uuid);

    BookResponseDto deleteById(String uuid);

    BookResponseDto updateById(String uuid, BookUpdateDto bookUpdateDto);

    List<BookResponseDto> getUsersBook(String userId);

    List<Book> findBooksByIds(List<String> ids);
}
