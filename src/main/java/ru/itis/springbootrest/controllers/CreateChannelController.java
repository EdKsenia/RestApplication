package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springbootrest.dto.ChannelDto;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.security.UserDetailsImpl;
import ru.itis.springbootrest.service.CreateChannelService;

@Controller
public class CreateChannelController {
    @Autowired
    private CreateChannelService service;

    @GetMapping("/createChannel")
    public String getChannelPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "createChannel";
    }

    @PostMapping("/createChannel")
    public String getChannel(ChannelDto form, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        form.setUser(user);
        service.createChannel(form);
        return "redirect:/myChannel";
    }
}
