package ru.itis.springbootrest.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.springbootrest.models.FileInfo;

import javax.servlet.http.HttpServletResponse;

public interface FileStorageService {
    FileInfo saveFile(MultipartFile file);

    void writeFileToResponse(String fileName, HttpServletResponse response);
}
