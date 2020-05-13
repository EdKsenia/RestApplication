package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootrest.dto.UserDto;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.service.UsersService;

import java.util.List;

@RestController
//@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

//    @GetMapping("/{user-id}")
//    public String getConcreteUserPage(@PathVariable("user-id") Long userId, Model model) {
//        User user = usersService.getConcreteUser(userId);
//        model.addAttribute("user", user);
//        return "user_page";
//    }

//    @GetMapping
//    public String getUsersPage(Model model) {
//        List<UserDto> users = usersService.getUsers();
//        model.addAttribute("users", users);
//        return "users_page";
//    }

//    @GetMapping("/search")
//    @ResponseBody
//    public List<UserDto> searchUsers(@RequestParam("name") String name) {
//        return usersService.search(name);
//    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user-id") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }






//    @Autowired
//    private CookieServiceImpl cookieService;
//
//    @GetMapping("/{user-id}")
//    public String getConcreteUserPage(@PathVariable("user-id") Long userId, Model model) {
//        UserDto user = usersService.getConcreteUser(userId);
//        model.addAttribute("user", user);
//        return "user_page";
//    }
//
//    @GetMapping
//    public String getUsersPage(@CookieValue(value = "AuthCookie", required = false) String cookie,
//                               Model model) {
//        String cookieValue = cookieService.checkCookie(cookie);
//        if (cookieValue == null) {
//            return "redirect:/signIn";
//        }
//        List<UserDto> users = usersService.getUsers();
//        model.addAttribute("users", users);
//        return "users_page";
//    }
//
//    @GetMapping("/search")
//    @ResponseBody
//    public List<UserDto> searchUsers(@RequestParam("query") String name)
//    {
//        return usersService.search(name);
//    }


}
