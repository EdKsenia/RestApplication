package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.VideoSearchResults;


public interface SearchService {
    VideoSearchResults searchVideos(String query, Integer page, Integer size);


}
