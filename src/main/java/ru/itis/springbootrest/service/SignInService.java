package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.SignInDto;
import ru.itis.springbootrest.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
