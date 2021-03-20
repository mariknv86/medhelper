package ru.mariknv86.medhelper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import ru.mariknv86.medhelper.service.RequestService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final static String UPLOAD_FOLDER = "uploads/";

    private final RequestService requestService;

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, ParserConfigurationException, SAXException {

        Random random = new Random();

        String type = file.getContentType().split("/")[1];
        String randomName = "file" + random.nextInt();
        String path = UPLOAD_FOLDER + randomName + "." + type;

        byte[] bytes = file.getBytes();

        File theDir = new File(UPLOAD_FOLDER);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        Files.write(Paths.get(path), bytes);

        requestService.loadFromFile(path);

        return ResponseEntity.ok(UPLOAD_FOLDER + randomName + "." + type);

    }
}
