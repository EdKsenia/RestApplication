package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.ChannelDto;
import ru.itis.springbootrest.dto.InformationChannelDto;
import ru.itis.springbootrest.models.Channel;

import java.util.List;

public interface ChannelsService {


    List<ChannelDto> getChannels();

    Channel getConcreteChannelByUserId(Long userId);

    Channel getConcreteChannelByChannelId(Long channelId);

    List<ChannelDto> search(String name);

    InformationChannelDto getInformation(Long channelId);

    void delete(Long id);

}
