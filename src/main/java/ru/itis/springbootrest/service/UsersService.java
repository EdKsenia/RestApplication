package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.UserDto;
import ru.itis.springbootrest.models.User;

import java.util.List;

public interface UsersService {
    List<UserDto> getUsers();

    User getConcreteUser(Long userId);

    List<UserDto> search(String name);

    List<UserDto> getAllUsers();

    void deleteUser(Long userId);
}
