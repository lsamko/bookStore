package com.example.bookstore.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private Long id;

    @Column(unique = true, nullable = false, name = "first_name")
    private String firstName;

    @Column(unique = true, nullable = false, name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "users")
    private List<Book> books = new ArrayList<>();

    @Basic
    @Column(unique = true, nullable = false, length = 255)
    private String userId;

}
