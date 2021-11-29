package com.example.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    private Long id;

   private String name;

   @ManyToOne
   private Book book;
}
