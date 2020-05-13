package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springbootrest.dto.VideoDto;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.security.UserDetailsImpl;
import ru.itis.springbootrest.service.AddVideoService;

@Controller
public class AddVideoController {
//    @GetMapping("/addNote")
//    public String getAddNotePage() {
//        return "addNote";
//    }

    @Autowired
    private AddVideoService service;

    @GetMapping("/addNote")
    public String getVideoAddingPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "addNote";
    }

    @PostMapping("/addNote")
    public String addVideo(VideoDto form, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        service.addVideo(form, user.getId());
        return "redirect:/myChannel";
    }
}
