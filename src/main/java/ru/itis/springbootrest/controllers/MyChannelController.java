package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootrest.dto.InformationChannelDto;
import ru.itis.springbootrest.dto.VideoDto;
import ru.itis.springbootrest.models.Channel;
import ru.itis.springbootrest.models.Video;
import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;
import ru.itis.springbootrest.service.AddVideoService;
import ru.itis.springbootrest.service.ChannelsService;
import ru.itis.springbootrest.service.UsersService;
import ru.itis.springbootrest.service.VideoService;


import java.util.List;

@RestController
public class MyChannelController {

    @Autowired
    private ChannelsService channelsService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private AddVideoService service;

    @Autowired
    private UsersService usersService;

//    @GetMapping("/myChannel")
//    public String getConcreteChannelPage(Authentication authentication, Model model) {
//        if (authentication != null) {
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//            model.addAttribute("user", usersService.getConcreteUser(userDetails.getUserId()));
////            Channel channel = channelsRepository.findOneById(userDetails.getUser().getId());
//            Channel channel = channelsService.getConcreteChannelByUserId(userDetails.getUserId());
//            if (channel != null) {
//                model.addAttribute("channel", channel);
//                List<Video> videos = videoService.getVideos(channel.getId());
//                model.addAttribute("videos", videos);
//                return "myChannel";
//            } else {
//                return "createChannel";
//            }
//
//        }
//        return "createChannel";
//    }

    @GetMapping("channel/{channel-id}/information")
    public ResponseEntity<InformationChannelDto> getInformation(@PathVariable("channel-id") Long channelId) {
        InformationChannelDto result = channelsService.getInformation(channelId);
        return ResponseEntity.ok(result);
    }

//    Rest

    @GetMapping("channel/{channel-id}/videos")
    public ResponseEntity<?> getVideos(@PathVariable("channel-id") Long channelId,
                                                 @RequestParam(value = "img", required = false) Long img) {
        List<Video> videos = videoService.getVideosByImg(channelId, img);
        return ResponseEntity.ok(videos);
    }

    @PostMapping("channel/{channel-id}/add")
    public ResponseEntity<List<Video>> addVideo(@PathVariable("channel-id") Long channelId, @RequestBody VideoDto video) {
        Channel channel = channelsService.getConcreteChannelByChannelId(channelId);
        Long userId = channel.getUser().getId();
        service.addVideo(video, userId);
        List<Video> videos = videoService.getVideos(channelId);
        return ResponseEntity.status(201).body(videos);
    }

    @DeleteMapping("channel/delete/{channel-id}")
    public ResponseEntity<?> deleteChannel(@PathVariable("channel-id") Long channelId) {
        channelsService.delete(channelId);
        return ResponseEntity.accepted().build();
}
}
