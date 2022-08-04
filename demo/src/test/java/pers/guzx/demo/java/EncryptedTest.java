package pers.guzx.demo.java;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pers.guzx.common.util.StringEncryptorUtil;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Slf4j
public class EncryptedTest {

    @Test
    void testJasypt(){
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("guzx970712");
        String metaData = "hello world";
        String encrypt = encryptor.encrypt(metaData);
        Assertions.assertNotNull(encrypt.length());
        String decrypt = encryptor.decrypt(encrypt);
        Assertions.assertEquals(metaData,decrypt);

        String username = "guzx";
        String password = "970712";
        String encryptUsername = encryptor.encrypt(username);
        String encryptPassword = encryptor.encrypt(password);
        log.info("username:{}\npassword:{}",encryptUsername,encryptPassword);
    }

    @Test
    void testEncryptor(){
        StringEncryptorUtil stringEncryptor = new StringEncryptorUtil();
        String name = "guzx";
        String encrypt = stringEncryptor.encrypt(name);
        log.info(encrypt);
        String decrypt = stringEncryptor.decrypt(encrypt);
        log.info(decrypt);
    }
}
