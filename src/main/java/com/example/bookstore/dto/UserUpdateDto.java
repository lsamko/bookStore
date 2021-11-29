package com.example.bookstore.dto;


import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

}
