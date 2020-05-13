package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.UserDto;

import java.util.List;

public interface UsersService {
    List<UserDto> getUsers();

    UserDto getConcreteUser(Long userId);

    List<UserDto> search(String name);
}
