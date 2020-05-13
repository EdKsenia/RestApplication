package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbootrest.dto.ChannelDto;
import ru.itis.springbootrest.dto.InformationChannelDto;
import ru.itis.springbootrest.models.Channel;
import ru.itis.springbootrest.repositories.ChannelsRepository;

import java.util.List;

import static ru.itis.springbootrest.dto.ChannelDto.from;

@Service
public class ChannelsServiceImpl implements ChannelsService {

    @Autowired
    private ChannelsRepository channelsRepository;

    @Override
    public List<ChannelDto> getChannels() {
        return from(channelsRepository.findAll());
    }

    @Override
    public Channel getConcreteChannelByUserId(Long userId) {
        Channel channel = channelsRepository.findOneByUserId(userId);
        return channel;
    }

    @Override
    public Channel getConcreteChannelByChannelId(Long channelId) {
        Channel channel = channelsRepository.findOneById(channelId);
        return channel;
    }

    @Override
    public List<ChannelDto> search(String name) {
        return null;
    }

    @Override
    public InformationChannelDto getInformation(Long channelId) {
        return channelsRepository.getInformationByChannel(channelId);
    }

    @Override
    public void delete(Long id) {
        channelsRepository.deleteById(id);
    }

//    @Override
//    public void create(ChannelDto channelDto) {
//        Channel channel = Channel.builder().
//                name(channelDto.getName())
//                .createdAt(LocalDateTime.now())
//                .about(channelDto.getAbout())
//                .user(channelDto.getUser())
//                .fileInfo(fileStorageService.saveFile(channelDto.getFile()))
//                .build();
//        channelsRepository.save(channel);
//    }
}
