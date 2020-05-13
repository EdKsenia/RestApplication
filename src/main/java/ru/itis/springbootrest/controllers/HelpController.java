package ru.itis.springbootrest.controllers;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.Authentication;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import ru.itis.springbootrest.dto.HelpMessageDto;
        import ru.itis.springbootrest.dto.UserDto;
        import ru.itis.springbootrest.models.User;
        import ru.itis.springbootrest.security.jwt.detais.UserDetailsImpl;
        import ru.itis.springbootrest.service.HelpService;
        import ru.itis.springbootrest.service.SmsService;
        import ru.itis.springbootrest.service.UsersService;

@Controller
public class HelpController {
    @Autowired
    private HelpService service;

    @Autowired
    private SmsService smsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/help")
    public String getHelp(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", usersService.getConcreteUser(userDetails.getUserId()));
        return "help";
    }

    @ResponseBody
    @PostMapping("/help")
    public void getHelp(Authentication authentication, HelpMessageDto form, @RequestParam("phone") String phone) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = usersService.getConcreteUser(userDetails.getUserId());
        form.setUser(user);
        service.help(form);
//        return "ok";
    }

//    @ResponseBody
//    @RequestMapping(path = "/help", produces = "application/text; charset=UTF-8")
//    public String signUp(HelpMessageDto form) {
//        System.out.println(form.getCode() + form.getConfirmCode());
//        if (form.getCode().equals(form.getConfirmCode())) {
//            service.help(form);
//            return "ok";
//        }
//        else {
//            return "error";
//        }
//    }

    @ResponseBody
    @RequestMapping(path = "/help/sendMessage", produces = "application/text; charset=UTF-8")
    public String sendMessage(@RequestParam("phone") String phone) {
        return smsService.sendMessage(phone);
    }
}
