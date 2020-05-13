package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.ChangeUserDto;

public interface ChangeProfileService {
    void changeProfile(ChangeUserDto form, Long id);
}
