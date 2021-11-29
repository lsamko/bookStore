package com.example.bookstore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Description {

    @Id
    private Long id;

    @OneToOne()
    private Book book;

    @Lob
    private String text;
}
