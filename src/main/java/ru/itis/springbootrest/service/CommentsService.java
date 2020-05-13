package ru.itis.springbootrest.service;

import ru.itis.springbootrest.dto.CommentDto;
import ru.itis.springbootrest.models.Comment;

import java.util.List;

public interface CommentsService {
    List<Comment> getComments(Long id);
    void addComment(CommentDto form);
}
