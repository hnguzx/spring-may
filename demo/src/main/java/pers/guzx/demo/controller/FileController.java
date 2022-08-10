package pers.guzx.demo.controller;


import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.util.FileUtils;
import pers.guzx.demo.entity.vo.CountryVO;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 25446
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private FileUtils fileUtils;

    @PostMapping("/uploadFile/single")
    public Result<String> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("description") String description) throws IOException {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return Result.failed();
        }
        log.info("fileName:{}, description:{}", uploadFile.getOriginalFilename(), description);
        String fileMd5 = fileUtils.saveToFile(uploadFile);
        return Result.succeed(fileMd5);
    }

    @PostMapping("/uploadFile/singleAndBean")
    public Result<String> uploadFileAndBean(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestPart("countryVO") CountryVO countryVO) throws IOException {
        if (uploadFile == null || uploadFile.isEmpty()) {
            return Result.failed();
        }
        log.info("fileName:{}, countryVO:{}", uploadFile.getOriginalFilename(), countryVO.toString());
        String fileMd5 = fileUtils.saveToFile(uploadFile);
        return Result.succeed(fileMd5);
    }

    @PostMapping("/uploadFile/list")
    public Result<List<String>> uploadFileList(@RequestParam("files") List<MultipartFile> files) throws IOException {
        if (files == null || files.isEmpty()) {
            return Result.failed();
        }
        List<String> collect = files.parallelStream().map(item -> {
            log.info("fileName:{}", item.getOriginalFilename());
            return fileUtils.saveToFile(item);
        }).collect(Collectors.toList());
        return Result.succeed(collect);
    }

    @PostMapping("/uploadFile/multiple")
    public Result<List<String>> uploadFiles(HttpServletRequest request) {
        List<MultipartFile> files = new ArrayList<>();
        MultipartFile file1 = ((MultipartHttpServletRequest) request).getFiles("file1").get(0);
        if (!file1.isEmpty()) {
            files.add(file1);
        }

        MultipartFile file2 = ((MultipartHttpServletRequest) request).getFiles("file2").get(0);
        if (!file2.isEmpty()) {
            files.add(file2);
        }
        if (files.isEmpty()) {
            return Result.failed();
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("parameterMap: " + parameterMap);

        List<String> uploadResult = new ArrayList<>();
        for (MultipartFile file : files) {
            log.info("fileName: " + file.getOriginalFilename());
            String fileMd5 = fileUtils.saveToFile(file);
            uploadResult.add(fileMd5);
        }
        return Result.succeed(uploadResult);
    }


    @GetMapping("/downloadFile/{fileMd5}")
    public void downloadFile(@PathVariable("fileMd5") String fileMd5, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("downloadFile", "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = fileUtils.downloadFile(response, fileMd5);
        if (bytes != null) {
            outputStream.write(bytes);
        }
    }

    @DeleteMapping("/deleteFile/{fileMd5}")
    public Result<Boolean> deleteFile(@PathVariable("fileMd5") String fileMd5) throws IOException {
        boolean result = fileUtils.deleteFile(fileMd5);
        return Result.succeed(result);
    }
}
