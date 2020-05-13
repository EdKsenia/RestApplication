package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootrest.models.Video;
import ru.itis.springbootrest.service.VideoService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/main")
    public String getMainPage(Model model, Authentication authentication) {
        List<Video> videos = videoService.getAll();
        model.addAttribute("videos", videos);
        boolean isAut = false;
        if(authentication!=null){
            isAut = true;
        }
        model.addAttribute("aut", isAut);
        return "main";
    }

  }
