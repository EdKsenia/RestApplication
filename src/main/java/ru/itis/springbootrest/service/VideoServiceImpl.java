package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springbootrest.dto.VideoDto;
import ru.itis.springbootrest.models.Video;
import ru.itis.springbootrest.repositories.VideoRepository;

import java.util.List;

import static ru.itis.springbootrest.dto.VideoDto.from;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepository;


    @Override
    public List<Video> getVideos(Long id) {
        return videoRepository.findAllByChannelId(id);
    }

    @Override
    public List<Video> getAll() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> getVideosByImg(Long id, Long img) {
        return videoRepository.findAllByChannelIdAndImgId(id, img);
    }

    @Override
    public VideoDto getConcreteVideo(Long videoId) {
        Video video = videoRepository.findOneById(videoId);
        return from(video);
    }

    @Override
    public void delete(Long id) {
        videoRepository.deleteById(id);
    }
}
