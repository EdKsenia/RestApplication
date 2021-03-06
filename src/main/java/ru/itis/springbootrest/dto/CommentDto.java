package ru.itis.springbootrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootrest.models.Comment;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.models.Video;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private Long id;
    private String text;
    private Video video;
    private User user;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder().
                id(comment.getId())
                .text(comment.getText())
                .video(comment.getVideo())
//                .user(UserDto.from(comment.getUser()))
                .user(comment.getUser())
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}
