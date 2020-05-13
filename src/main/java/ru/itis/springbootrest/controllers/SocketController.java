package ru.itis.springbootrest.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootrest.security.UserDetailsImpl;

@Controller
public class SocketController {
    @GetMapping("/index")
    public String getIndexPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("pageId", userDetails.getUser().getName());
//        model.addAttribute("pageId", UUID.randomUUID().toString());
        return "index";
    }
}

