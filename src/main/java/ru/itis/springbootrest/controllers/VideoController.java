package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootrest.dto.CommentDto;
import ru.itis.springbootrest.models.Channel;
import ru.itis.springbootrest.models.Comment;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.models.Video;
import ru.itis.springbootrest.repositories.ChannelsRepository;
import ru.itis.springbootrest.repositories.VideoRepository;
import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;
import ru.itis.springbootrest.service.CommentsService;
import ru.itis.springbootrest.service.UsersService;
import ru.itis.springbootrest.service.VideoService;

import java.util.List;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/note{note-id}")
    public String getConcreteNotePage(@PathVariable("note-id") Long noteId, Authentication authentication, Model model) {
        if (authentication != null) {
            Video video = videoRepository.getOne(noteId);
            model.addAttribute("video", video);
            Channel channel = video.getChannel();
            User user = channel.getUser();
            model.addAttribute("user", user);
            model.addAttribute("channel", channel);
//            Video prev = videoRepository.getOne(noteId - 1);
//            if(prev!=null){
//                model.addAttribute("prev", prev);
//            }
//            Video next = videoRepository.getOne(noteId + 1);
//            if(next!=null){
//                model.addAttribute("next", next);
//            }
//            VideoDto video = videoService.getConcreteVideo(noteId);
            List<Comment> comments = commentsService.getComments(noteId);

            model.addAttribute("comments", comments);
            return "note";
        }
        return "myChannel";
    }

    @PostMapping("/comment{note-id}")
    public String addComment(@PathVariable("note-id") Long noteId, Authentication authentication, CommentDto form) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            form.setUser(usersService.getConcreteUser(userDetails.getUserId()));
            Video video = videoRepository.getOne(noteId);
            form.setVideo(video);
            commentsService.addComment(form);
        }
        return "redirect:/note" + noteId;
    }

    @PostMapping("/deleteNote{note-id}")
    public String deleteVideo(@PathVariable("note-id") Long noteId, Authentication authentication){
        if (authentication != null) {
            Video video = videoRepository.getOne(noteId);
            videoService.delete(video.getId());
        }
        return "redirect:/myChannel";
    }


}
