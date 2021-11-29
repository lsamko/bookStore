package com.example.bookstore.dto;

import com.example.bookstore.entities.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseDto {

    private String name;
    private List<User> users = new ArrayList<>();
}
