package ru.itis.springbootrest.security.jwt.detais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.repositories.UsersRepository;
import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> userOptional = usersRepository.findByEmail(email);
//        if (userOptional.isPresent()) {
//            return new UserDetailsImpl(userOptional.getId());
//        }
//        throw new UsernameNotFoundException("User not found");
        return null;
    }
}