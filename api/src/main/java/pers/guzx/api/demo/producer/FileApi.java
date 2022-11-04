package pers.guzx.api.demo.producer;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.guzx.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileApi {
    @PostMapping("/uploadFile/single")
    public Result<String> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("description") String description);

    @PostMapping("/uploadFile/singleAndBean")
    public Result<String> uploadFileAndBean(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestPart("countryVO") CountryVO countryVO);

    @PostMapping("/uploadFile/list")
    public Result<List<String>> uploadFileList(@RequestParam("files") List<MultipartFile> files);

    @PostMapping("/uploadFile/multiple")
    public Result<List<String>> uploadFiles(HttpServletRequest request);


    @GetMapping("/downloadFile/{fileMd5}")
    public void downloadFile(@PathVariable("fileMd5") String fileMd5, HttpServletRequest request, HttpServletResponse response);

    @DeleteMapping("/deleteFile/{fileMd5}")
    public Result<Boolean> deleteFile(@PathVariable("fileMd5") String fileMd5);
}
