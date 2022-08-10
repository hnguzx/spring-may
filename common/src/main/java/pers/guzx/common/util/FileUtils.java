package pers.guzx.common.util;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pers.guzx.common.entity.dto.Result;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;

/**
 * @author 25446
 */
@Slf4j
@ConfigurationProperties(prefix = "file.storage")
@Component
@Data
public class FileUtils {

    private String path;

    @PostConstruct
    public void init() {
        if (StringUtils.hasLength(path)) {
            File tmpDir = new File(path);
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }
        }
    }

    public String saveToFile(MultipartFile multipartFile) {
        String name = multipartFile.getOriginalFilename();
        long remainSize = multipartFile.getSize();
        log.info("upload file name={}, file size={}", name, remainSize);
        if (remainSize == 0) {
            return null;
        }
        InputStream inputStream = null;
        FileOutputStream outStream = null;
        String md5DigestAsHex;
        try {
            md5DigestAsHex = DigestUtils.md5DigestAsHex(multipartFile.getInputStream());
            inputStream = multipartFile.getInputStream();

            File file = new File(path + "/" + md5DigestAsHex);
            outStream = new FileOutputStream(file);

            int readLen;
            byte[] buffer = new byte[1024];
            while (remainSize > 0) {
                readLen = inputStream.read(buffer);
                if (readLen == -1) {
                    break;
                }

                remainSize -= readLen;
                outStream.write(buffer, 0, readLen);
            }

            return md5DigestAsHex;
        } catch (Exception e) {
            log.error("write temp file failed!", e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error("inputStream close failed!", e);
                }
            }

            if (outStream != null) {
                try {
                    outStream.close();
                } catch (Exception e) {
                    log.error("outStream close failed!", e);
                }
            }
        }
    }

    public byte[] downloadFile(HttpServletResponse response, String fileMd5) {
        File tempFile = new File(path, fileMd5);
        if (!tempFile.exists()) {
            return null;
        }
        byte[] bytes = FileUtil.readBytes(tempFile);
        response.setHeader("ContentLength", String.valueOf(bytes.length));
        String md5DigestAsHex = DigestUtils.md5DigestAsHex(bytes);
        response.setHeader("X-Down-Hash", md5DigestAsHex);
        response.setHeader("ContentType", "application/octet-stream");
        return bytes;
    }

    public boolean deleteFile(String fileMd5) {
        File tempFile = new File(path + "/" + fileMd5);
        if (!tempFile.exists()) {
            log.warn("File not exists");
            return false;
        }
        return FileUtil.del(tempFile);
    }
}
