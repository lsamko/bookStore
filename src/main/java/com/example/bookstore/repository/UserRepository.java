package com.example.bookstore.repository;

import com.example.bookstore.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByLastName(String lastName);

    boolean existsUserByFirstName(String firstName);

    Optional<User> findUserByUserId(String uuid);

    void deleteUserByUserId(String uuid);

    boolean existsByUserId(String uuid);
}
