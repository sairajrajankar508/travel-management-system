//package com.travel.controller;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/files")
//public class FileUploadController {
//
//    // ABSOLUTE SAFE PATH
//    private static final String UPLOAD_DIR =
//            System.getProperty("user.dir")
//            + File.separator
//            + "file_uploads"
//            + File.separator;
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(
//            @RequestParam("file") MultipartFile file
//    ) {
//
//        try {
//
//            // CREATE DIRECTORY
//            File directory = new File(UPLOAD_DIR);
//
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // FILE NAME
//            String fileName = file.getOriginalFilename();
//
//            // FULL FILE PATH
//            File destination = new File(
//                    UPLOAD_DIR + fileName
//            );
//
//            // SAVE FILE
//            file.transferTo(destination);
//
//            return ResponseEntity.ok(
//                    "File uploaded successfully: "
//                            + destination.getAbsolutePath()
//            );
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//            return ResponseEntity.badRequest()
//                    .body("File upload failed");
//        }
//    }
//}



package com.travel.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FileUploadController {

    // UPLOAD DIRECTORY (project root ke andar)
    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
                    + File.separator
                    + "file_uploads"
                    + File.separator;

    // ============================
    // UPLOAD FILE API
    // ============================
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            // 1. Directory create karo if not exists
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 2. Safe unique filename generate karo (IMPORTANT)
            String originalName = file.getOriginalFilename();

            String extension = "";

            if (originalName != null && originalName.contains(".")) {
                extension = originalName.substring(originalName.lastIndexOf("."));
            }

            String fileName = UUID.randomUUID() + extension;

            // 3. Save path
            Path filePath = Paths.get(UPLOAD_DIR, fileName);

            // 4. Save file
            Files.write(filePath, file.getBytes());

            // 5. Return public URL
            String fileUrl = "/file_uploads/" + fileName;

            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed");
        }
    }
}