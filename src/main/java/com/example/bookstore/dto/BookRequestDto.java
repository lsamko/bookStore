package com.example.bookstore.dto;


import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class BookRequestDto {

    @NotNull
    private String name;

    @NotNull
    private int bookId;

    private String userId;



}
