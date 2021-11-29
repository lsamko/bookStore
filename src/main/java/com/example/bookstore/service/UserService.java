package com.example.bookstore.service;

import com.example.bookstore.dto.UserRequestDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.dto.UserUpdateDto;
import com.example.bookstore.entities.Book;
import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto, List<Book> books);

    List<UserResponseDto> getAllUsers(Integer from, Integer size);

    UserResponseDto findById(String uuid);

    void deleteById(String uuid);

    UserResponseDto updateById(String uuid, UserUpdateDto userUpdateDto);

    boolean existsByUserId(String userId);
}
