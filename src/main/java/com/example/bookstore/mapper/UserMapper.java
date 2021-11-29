package com.example.bookstore.mapper;

import com.example.bookstore.dto.UserRequestDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.entities.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", expression = "java(java.util.UUID.randomUUID().toString())")
    User fromRequestDtoToEntity(UserRequestDto userRequestDto);

    UserResponseDto fromEntityToResponseDto(User user);

    List<UserResponseDto> fromEntityListToResponseDtoList(List<User> users);

}
