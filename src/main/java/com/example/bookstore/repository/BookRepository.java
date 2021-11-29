package com.example.bookstore.repository;

import com.example.bookstore.entities.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsBookByname(String name);

    Optional<Book> findBookByBookId(String uuid);

    Optional<Book> deleteBookByBookId(String uuid);

    List<Book> findBookByUserId(String userId);

    List<Book> findBooksByBookIdIn(List<String> ids);
}
