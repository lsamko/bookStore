package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookRequestDto;
import com.example.bookstore.dto.BookResponseDto;
import com.example.bookstore.entities.Book;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "bookId")
    Book fromRequestDtoToEntity(BookRequestDto bookRequestDto);

    BookResponseDto fromEntityToResponseDto(Book book);

    List<BookResponseDto> fromEntityListToResponseDtoList(List<Book> books);
}
