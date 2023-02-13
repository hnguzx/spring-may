package pers.guzx.api.demo.producer;

import pers.guzx.common.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@FeignClient("producer-server")
public interface FileApi {
    @PostMapping("/uploadFile/single")
    Result<String> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("description") String description);

    @PostMapping("/uploadFile/singleAndBean")
    Result<String> uploadFileAndBean(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestPart("countryVO") CountryVO countryVO);

    @PostMapping("/uploadFile/list")
    Result<List<String>> uploadFileList(@RequestParam("files") List<MultipartFile> files);

    @PostMapping("/uploadFile/multiple")
    Result<List<String>> uploadFiles(HttpServletRequest request);


    @GetMapping("/downloadFile/{fileMd5}")
    void downloadFile(@PathVariable("fileMd5") String fileMd5, HttpServletRequest request, HttpServletResponse response);

    @DeleteMapping("/deleteFile/{fileMd5}")
    Result<Boolean> deleteFile(@PathVariable("fileMd5") String fileMd5);
}
