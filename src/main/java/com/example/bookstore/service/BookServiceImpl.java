package com.example.bookstore.service;

import com.example.bookstore.dto.BookRequestDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.dto.BookUpdateDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.exception.BookNotFoundException;
import com.example.bookstore.exception.BookWithNameAlreadyExistsException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import com.example.bookstore.mapper.BookMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.bookstore.repository.BookRepository;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        Book book = bookMapper.fromRequestDtoToEntity(bookRequestDto);
        String name = bookRequestDto.getName();
        if (this.isBookWithNameExists(name)) {
            throw new BookWithNameAlreadyExistsException(
                String.format("Book with name '%s' already exists", name)
            );
        }
        Book bookToSave = bookRepository.save(book);

        return bookMapper.fromEntityToResponseDto(bookToSave);
    }

    private Book mapBookFromDto(BookRequestDto bookRequestDto) {
        return new Book(bookRequestDto);
    }

    private boolean isBookWithNameExists(String name) {
        return bookRepository.existsBookByname(name);
    }

    @Override
    public List<BookResponseDto> findAll(Integer from, Integer size) {
        Pageable paging = PageRequest.of(from, size);
        Page<Book> pageResult = bookRepository.findAll(paging);
        return bookMapper.fromEntityListToResponseDtoList(pageResult.getContent());
    }

    @Override
    public BookResponseDto findById(String uuid) {
        Book book = bookRepository.findBookByBookId(uuid)
            .orElseThrow(() -> new BookNotFoundException("Could not find book " + uuid));
        return bookMapper.fromEntityToResponseDto(book);
    }

    @Override
    public BookResponseDto deleteById(String uuid) {
        Book book = bookRepository.deleteBookByBookId(uuid)
            .orElseThrow(() -> new BookNotFoundException("Could not find book " + uuid));
        return bookMapper.fromEntityToResponseDto(book);
    }

    @Override
    public BookResponseDto updateById(String uuid, BookUpdateDto bookUpdateDto) {
        Book bookToUpdate = bookRepository.findBookByBookId(uuid)
            .orElseThrow(() -> new BookNotFoundException("Could not find book " + uuid));
        String newName = bookUpdateDto.getName();
        if (this.isNameChanged(bookToUpdate, newName) && this.isBookWithNameExists(newName)) {
            throw new BookWithNameAlreadyExistsException(
                String.format("Book with name '%s' already exists", newName)
            );
        }
        bookToUpdate.setName(bookUpdateDto.getName());
        bookRepository.save(bookToUpdate);
        return bookMapper.fromEntityToResponseDto(bookToUpdate);
    }

    private boolean isNameChanged(Book bookToUpdate, String newname) {
        return !bookToUpdate.getName().equals(newname);
    }

    @Override
    public List<BookResponseDto> getUsersBook(String userId) {
        List<Book> booksByUsers = bookRepository.findBookByUserId(userId);
        return booksByUsers.stream()
            .map(bookMapper::fromEntityToResponseDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<Book> findBooksByIds(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            return bookRepository.findBooksByBookIdIn(ids);
        }
        return Collections.emptyList();
    }
}
