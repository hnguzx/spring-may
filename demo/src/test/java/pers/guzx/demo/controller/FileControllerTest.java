package pers.guzx.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import pers.guzx.common.util.FileUtils;

import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMybatis
@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileUtils mockFileUtils;

    @Test
    void testUploadFile() throws Exception {
        // Setup
        when(mockFileUtils.saveToFile(any(MultipartFile.class))).thenReturn("file MD5");

        //File file = new File("D:\\project\\insight-server\\account-info-service\\.gitignore");
        //MockMultipartFile multipartFile = new MockMultipartFile("uploadFile", ".gitignore",
        //        MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));

        MockMultipartFile multipartFile = new MockMultipartFile("uploadFile", "originalFilename",
                MediaType.TEXT_PLAIN_VALUE, "content".getBytes(StandardCharsets.UTF_8));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/uploadFile/single")
                        .file(multipartFile)
                        .param("description", "description")
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testUploadFileAndBean() throws Exception {
        // Setup
        when(mockFileUtils.saveToFile(any(MultipartFile.class))).thenReturn("file MD5");


        MockMultipartFile multipartFile = new MockMultipartFile("uploadFile", "originalFilename",
                MediaType.TEXT_PLAIN_VALUE, "content".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile json = new MockMultipartFile("countryVO", "",
                MediaType.APPLICATION_JSON_VALUE, ("{\n" +
                "\"code\":10005,\n" +
                "\"name\":\"澳大利亚联邦\",\n" +
                "\"englishName\":\"Commonwealth of Australia\",\n" +
                "\"island\":\"大洋洲\",\n" +
                "\"language\":\"英语\",\n" +
                "\"population\":25690000,\n" +
                "\"grownDate\":\"17880126\"\n" +
                "}").getBytes());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/uploadFile/singleAndBean")
                        .file(multipartFile)
                        .file(json)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testUploadFiles() throws Exception {
        // Setup
        when(mockFileUtils.saveToFile(any(MultipartFile.class))).thenReturn("result");

        MockMultipartFile multipartFile1 = new MockMultipartFile("files", "originalFilename1",
                MediaType.TEXT_PLAIN_VALUE, "content".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile multipartFile2 = new MockMultipartFile("files", "originalFilename2",
                MediaType.TEXT_PLAIN_VALUE, "content".getBytes(StandardCharsets.UTF_8));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(multipart("/uploadFile/list")
                        .file(multipartFile1)
                        .file(multipartFile2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testDownloadFile() throws Exception {
        // Setup
        when(mockFileUtils.downloadFile(any(HttpServletResponse.class), eq("fileMd5")))
                .thenReturn("return file content".getBytes());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/downloadFile/{fileMd5}", "fileMd5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testDownloadFile_FileUtilsReturnsNull() throws Exception {
        // Setup
        when(mockFileUtils.downloadFile(any(HttpServletResponse.class), eq("fileMd5"))).thenReturn(null);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/downloadFile/{fileMd5}", "fileMd5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testDeleteFile() throws Exception {
        // Setup
        when(mockFileUtils.deleteFile("fileMd5")).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/deleteFile/{fileMd5}", "fileMd5")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

}
