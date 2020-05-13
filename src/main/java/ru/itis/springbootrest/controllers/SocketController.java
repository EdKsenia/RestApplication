package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;
import ru.itis.springbootrest.service.UsersService;

@Controller
public class SocketController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/index")
    public String getIndexPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("pageId", usersService.getConcreteUser(userDetails.getUserId()).getName());
//        model.addAttribute("pageId", UUID.randomUUID().toString());
        return "index";
    }
}

