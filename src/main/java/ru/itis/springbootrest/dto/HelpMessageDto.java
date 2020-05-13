package ru.itis.springbootrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springbootrest.models.HelpMessage;
import ru.itis.springbootrest.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelpMessageDto {
    private Long id;
    private String text;
    private String name;
    private String phone;
    private User user;

    public static HelpMessageDto from(HelpMessage message){
        return HelpMessageDto.builder().
                id(message.getId())
                .text(message.getText())
                .name(message.getName())
                .phone(message.getPhone())
                .user(message.getUser())
                .build();

    }

//    public static List<ChannelDto> from(List<Channel> channels) {
//        return channels.stream()
//                .map(ChannelDto::from)
//                .collect(Collectors.toList());
//    }
}
