package ru.itis.springbootrest.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}
