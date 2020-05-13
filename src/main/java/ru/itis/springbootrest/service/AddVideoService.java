package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.VideoDto;

public interface AddVideoService {
    void addVideo(VideoDto form, Long id);
}
