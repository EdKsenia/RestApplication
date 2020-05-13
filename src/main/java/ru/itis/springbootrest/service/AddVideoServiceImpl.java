package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbootrest.dto.VideoDto;
import ru.itis.springbootrest.models.Channel;
import ru.itis.springbootrest.models.Video;
import ru.itis.springbootrest.repositories.ChannelsRepository;
import ru.itis.springbootrest.repositories.VideoRepository;

import java.time.LocalDateTime;
@Service
public class AddVideoServiceImpl implements AddVideoService {
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ChannelsRepository channelsRepository;

    @Override
    public void addVideo(VideoDto form, Long id) {
        Channel channel = channelsRepository.findOneByUserId(id);
        form.setChannel(channel);
        Video video = Video.builder().
                name(form.getName())
                .createdAt(LocalDateTime.now())
                .description(form.getDescription())
                .channel(form.getChannel())
                .img(fileStorageService.saveFile(form.getImg()))
                .video(fileStorageService.saveFile(form.getVideo()))
                .build();
        videoRepository.save(video);
    }
}
