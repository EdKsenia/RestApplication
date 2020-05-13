package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.VideoDto;
import ru.itis.springbootrest.models.Video;

import java.util.List;

public interface VideoService {

    List<Video> getVideos(Long id);

    List<Video> getAll();

    List<Video> getVideosByImg(Long id, Long img);
    VideoDto getConcreteVideo(Long videoId);

    void delete(Long id);
}
