package pers.guzx.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pers.guzx.common.entity.dto.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 25446
 */
@Slf4j
@RestController
public class FileController {

    @Value("${file.storagePath}")
    private String fileStorePath;

    @PutMapping("/uploadFile/single")
    public Result uploadFile(MultipartFile uploadFile, String description) {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return Result.failed();
        }
        log.info("fileName:{}, description:{}", uploadFile.getOriginalFilename(), description);

        File file = new File(fileStorePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(fileStorePath, uploadFile.getOriginalFilename());
        if (file.exists()) {
            log.warn("File already exists");
            return Result.succeed();
        }
        try {
            uploadFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.succeed();
    }

    @PutMapping("/uploadFile/multiple")
    public Result uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (files.isEmpty()) {
            return Result.failed();
        }

        for (MultipartFile file : files) {
            log.info("fileName: " + file.getOriginalFilename());
            File tempFile = new File(fileStorePath);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            tempFile = new File(fileStorePath, file.getOriginalFilename());
            if (tempFile.exists()) {
                log.warn("File already exists");
            }
            try {
                file.transferTo(tempFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Result.succeed();
    }

    @GetMapping("/downloadFile")
    public void downloadFile(String fileName) {
        System.out.println("    Downloading file: " + fileName);
    }
}
