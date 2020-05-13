package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbootrest.dto.UserDto;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.repositories.UsersRepository;

import java.util.List;

import static ru.itis.springbootrest.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public User getConcreteUser(Long userId){
        User user = usersRepository.getOne(userId);
        return user;
    }

    @Override
    public List<UserDto> search(String name){
        return from(usersRepository.findAllByNameContainsIgnoreCase(name));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }
//    @Autowired
//    private CookieValuesRepository cookieValuesRepository;
}
