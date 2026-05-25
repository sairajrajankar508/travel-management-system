package com.travel.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileUploadController {

    // ABSOLUTE SAFE PATH
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
            + File.separator
            + "file_uploads"
            + File.separator;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            // CREATE DIRECTORY
            File directory = new File(UPLOAD_DIR);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // FILE NAME
            String fileName = file.getOriginalFilename();

            // FULL FILE PATH
            File destination = new File(
                    UPLOAD_DIR + fileName
            );

            // SAVE FILE
            file.transferTo(destination);

            return ResponseEntity.ok(
                    "File uploaded successfully: "
                            + destination.getAbsolutePath()
            );

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity.badRequest()
                    .body("File upload failed");
        }
    }
}