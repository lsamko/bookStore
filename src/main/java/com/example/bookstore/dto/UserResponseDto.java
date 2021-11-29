package com.example.bookstore.dto;

import com.example.bookstore.entities.Book;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

    private String firstName;
    private String lastName;
    private List<Book> books = new ArrayList<>();
}
