package ru.itis.springbootrest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootrest.models.Video;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findOneByChannelId(Long channel);
    Video findOneById(Long id);
    List<Video> findAllByChannelId(Long channel);
    List<Video> findAllByChannelIdAndImgId(Long channel, Long img);
    List<Video> findAll();
    void deleteById(Long id);
    List<Video> findAllByNameContainsIgnoreCase(String name);

    @Query("from Video video where " +
        "(upper(video.name) like concat('%', upper(:query), '%') or " +
        "upper(video.channel.name) like concat ('%', upper(:query), '%') or " +
        "upper(video.channel.user.email) like concat ('%', upper(:query), '%') or " +
        "upper(video.channel.user.name) like concat ('%', upper(:query), '%'))")
    Page<Video> search(@Param("query") String query, Pageable pageable);
}
