package com.example.bookstore.service;

import com.example.bookstore.dto.UserRequestDto;
import com.example.bookstore.dto.UserResponseDto;
import com.example.bookstore.dto.UserUpdateDto;
import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.User;
import com.example.bookstore.exception.UserNotFoundException;
import com.example.bookstore.exception.UserWithNameAlreadyExistsException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.bookstore.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.bookstore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto, List<Book> books) {
        User user = userMapper.fromRequestDtoToEntity(userRequestDto);
        user.setBooks(books);
        String newLastName = userRequestDto.getLastName();
        String newFirstName = userRequestDto.getFirstName();
        if (this.isUserWithLastNameExists(newLastName)) {
            throw new UserWithNameAlreadyExistsException(
                String.format("User with lastname '%s' already exists", newLastName)
            );
        } else if (this.isUserWithFirstNameExists(newFirstName)) {
            throw new UserWithNameAlreadyExistsException(
                String.format("User with firstname '%s' already exists", newFirstName)
            );
        }
        User userToSave = userRepository.save(user);
        return userMapper.fromEntityToResponseDto(userToSave);
    }

    @Override
    public List<UserResponseDto> getAllUsers(Integer from, Integer size) {
        Pageable paging = PageRequest.of(from, size);
        Page<User> users = userRepository.findAll(paging);
        return userMapper.fromEntityListToResponseDtoList(users.getContent());
    }

    @Override
    public UserResponseDto findById(String uuid) {
        User user = userRepository.findUserByUserId(uuid)
            .orElseThrow(() -> new UserNotFoundException("Could not find user: " + uuid));
        return userMapper.fromEntityToResponseDto(user);
    }

    @Override
    public void deleteById(String uuid) {
        userRepository.deleteUserByUserId(uuid);
    }

    @Override
    public UserResponseDto updateById(String uuid, UserUpdateDto userUpdateDto) {
        User toUpdate = userRepository.findUserByUserId(uuid)
            .orElseThrow(() -> new UserNotFoundException("Could not find user: " + uuid));
        String newLastName = userUpdateDto.getLastName();
        String newFirstName = userUpdateDto.getFirstName();
        if (this.isNameChanged(toUpdate, newLastName) && this.isUserWithLastNameExists(newLastName)) {
            throw new UserWithNameAlreadyExistsException(
                String.format("User with lastname '%s' already exists", newLastName)
            );
        } else if (this.isUserWithFirstNameExists(newFirstName)) {
            throw new UserWithNameAlreadyExistsException(
                String.format("User with firstname '%s' already exists", newFirstName)
            );
        }
        toUpdate.setLastName(userUpdateDto.getLastName());
        toUpdate.setFirstName(userUpdateDto.getFirstName());
        userRepository.save(toUpdate);
        return userMapper.fromEntityToResponseDto(toUpdate);
    }

    private boolean isNameChanged(User toUpdate, String newLastName) {
        return !toUpdate.getLastName().equals(newLastName);
    }

    @Override
    public boolean existsByUserId(String userId) {
        return userRepository.existsByUserId(userId);
    }


    private boolean isUserWithLastNameExists(String lastName) {
        return userRepository.existsUserByLastName(lastName);
    }

    private boolean isUserWithFirstNameExists(String firstName) {
        return userRepository.existsUserByFirstName(firstName);
    }
}