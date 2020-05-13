package ru.itis.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootrest.dto.ChangeUserDto;
import ru.itis.springbootrest.models.FileInfo;
import ru.itis.springbootrest.models.User;
import ru.itis.springbootrest.repositories.UsersRepository;

import java.util.Optional;

@Component
public class ChangeProfileServiceImpl implements ChangeProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private FileStorageService fileStorageService;


    @Override
    public void changeProfile(ChangeUserDto form, Long id) {
        Optional<User> userToUpdate = usersRepository.findById(id);
        MultipartFile file = form.getImg();
        FileInfo fileForUpdate;
        if (userToUpdate.isPresent()) {
            User user = userToUpdate.get();
            if (file != null){
                fileForUpdate =  fileStorageService.saveFile(file);
            } else {
                fileForUpdate = user.getImg();
            }
            user.setName(form.getName());
            user.setAbout(form.getAbout());
            user.setImg(fileForUpdate);

            usersRepository.save(user);

        }

    }
}