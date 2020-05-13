package ru.itis.springbootrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootrest.dto.InformationChannelDto;
import ru.itis.springbootrest.models.Channel;

public interface ChannelsRepository extends JpaRepository<Channel, Long> {
    Channel findOneByUserId(Long user);
    Channel findOneById(Long id);
    void deleteById(Long id);

    @Query("select new ru.itis.springbootrest.dto.InformationChannelDto(channel.name, count(video)) from Channel channel left join channel.videos " +
            "as video where channel.id = :channelId group by channel.id")
    InformationChannelDto getInformationByChannel(@Param("channelId") Long id);
}
