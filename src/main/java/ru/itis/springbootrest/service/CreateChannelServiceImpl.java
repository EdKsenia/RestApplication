package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootrest.dto.ChannelDto;
import ru.itis.springbootrest.models.Channel;
import ru.itis.springbootrest.repositories.ChannelsRepository;

import java.time.LocalDateTime;

@Component
public class CreateChannelServiceImpl implements CreateChannelService {
    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    private ChannelsRepository channelsRepository;

    @Override
    public void createChannel(ChannelDto channelDto) {
        Channel channel = Channel.builder().
                name(channelDto.getName())
                .createdAt(LocalDateTime.now())
                .about(channelDto.getAbout())
                .user(channelDto.getUser())
                .fileInfo(fileStorageService.saveFile(channelDto.getFile()))
                .build();
        channelsRepository.save(channel);
    }
}
