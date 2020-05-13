package ru.itis.springbootrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootrest.service.FileStorageService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StorageController {

    @Autowired
    private FileStorageService service;

    @GetMapping("/storage")
    public String getStoragePage(){
        return "file_upload";
    }

//    @PostMapping("/files")
//    public ResponseEntity<String> handleFileUpload(@RequestParam("file")MultipartFile file){
//        String filePath = service.saveFile(file);
//        return ResponseEntity
//                .ok()
//                .body(filePath);
//    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        service.writeFileToResponse(fileName, response);
    }

}

