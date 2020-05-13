package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootrest.dto.ChangeUserDto;
import ru.itis.springbootrest.dto.ProfileForm;
import ru.itis.springbootrest.dto.UserDto;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;
import ru.itis.springbootrest.service.ChangeProfileService;
import ru.itis.springbootrest.service.ChannelsService;
import ru.itis.springbootrest.service.UsersService;

import javax.validation.Valid;

@RestController
public class ProfileController {
    @Autowired
    private ChannelsService channelsService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private ChangeProfileService changeProfileService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        System.out.println(userDetails);
        return ResponseEntity.ok(UserDto.builder()
                .name(userDetails.getUsername())
                .id(userDetails.getUserId())
                .build());

    }

    @GetMapping("/changeProfile")
    public String getChangeProfile(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUserId());
        model.addAttribute("profileForm", new ProfileForm());
        return "changeProfile";
    }

    @PostMapping("/changeProfile")
    public String updateProfile(Authentication authentication,@Valid ChangeUserDto userDto,
                                BindingResult bindingResult, Model model) {
        System.out.println(userDto);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = usersService.getConcreteUser(userDetails.getUserId());
        model.addAttribute("user", user);
        System.out.println(bindingResult.getAllErrors());
        model.addAttribute("profileForm", userDto);
        if(!bindingResult.hasErrors()){
            changeProfileService.changeProfile(userDto, user.getId());
            return "profile";
        }
        else{
            return "changeProfile";
        }
    }

}
