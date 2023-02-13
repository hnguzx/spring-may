package pers.guzx.common.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.guzx.common.util.StringEncryptorUtils;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Configuration
@ConditionalOnProperty(prefix = "jasypt", name = "encryptor")
public class EncryptionConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        return new StringEncryptorUtils();
    }
}
