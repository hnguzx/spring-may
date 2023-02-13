package pers.guzx.common.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe 自定义字符串加密解密
 */
@Slf4j
@Component("stringEncryptorUtil")
public class StringEncryptorUtils implements StringEncryptor {

    @Override
    public String encrypt(String s) {
        if (StringUtils.hasLength(s)){
            return s;
        }
        return null;
    }

    @Override
    public String decrypt(String s) {
        if (StringUtils.hasLength(s)){
            return s;
        }
        return null;
    }

}
