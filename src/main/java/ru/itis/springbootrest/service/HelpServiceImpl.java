package ru.itis.springbootrest.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.springbootrest.dto.HelpMessageDto;
import ru.itis.springbootrest.models.HelpMessage;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.repositories.HelpRepository;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Component
public class HelpServiceImpl implements HelpService {
    @Autowired
    private ExecutorService executorService;

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public void help(HelpMessageDto form) {
        HelpMessage message = HelpMessage.builder()
                .phone(form.getPhone())
                .name(form.getName())
                .text(form.getText())
                .user(form.getUser())
                .createdAt(LocalDateTime.now())
                .build();

        helpRepository.save(message);

        User user = form.getUser();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
        Map<String, Object> root = new HashMap<>();
        root.put("username", user.getName());
        root.put("text", message.getText());
        root.put("phone", message.getPhone());
        root.put("time", message.getCreatedAt());

        Template temp = null;
        try {
            temp = cfg.getTemplate("help_message.ftlh");
            Writer out = new OutputStreamWriter(System.out);
            temp.process(root, out);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        Template finalTemp = temp;
        executorService.submit(() ->
        {
            try {
                emailService.sendMail("Help Message", FreeMarkerTemplateUtils.processTemplateIntoString(finalTemp, root), user.getEmail());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        });

    }
}
