package producer.java;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import pers.guzx.common.util.StringEncryptorUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Slf4j
public class EncryptedTest {

    @Test
    void testJasypt() {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("guzx970712");
        String metaData = "hello world";
        String encrypt = encryptor.encrypt(metaData);
        Assertions.assertNotNull(encrypt.length());
        String decrypt = encryptor.decrypt(encrypt);
        Assertions.assertEquals(metaData, decrypt);

        Assumptions.assumingThat(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                return 1 > 0;
            }
        },new Executable() {
            @Override
            public void execute() throws Throwable {
                log.info("Encrypted");
            }
        });

        String username = "pers/guzx";
        String password = "970712";
        String encryptUsername = encryptor.encrypt(username);
        String encryptPassword = encryptor.encrypt(password);
        log.info("username:{}\npassword:{}", encryptUsername, encryptPassword);

        Assertions.assertAll(new Executable() {
            @Override
            public void execute() throws Throwable {
                Assertions.assertEquals(username, "pers/guzx");
            }
        });
    }

    @DisplayName("encryptor")
    @Timeout(value = 1000, unit = TimeUnit.MICROSECONDS)
    @RepeatedTest(5)
    @Test
    void testEncryptor() {
        StringEncryptorUtils stringEncryptor = new StringEncryptorUtils();
        String name = "pers/guzx";
        Assumptions.assumeFalse(false);
        String encrypt = stringEncryptor.encrypt(name);
        log.info(encrypt);
        String decrypt = stringEncryptor.decrypt(encrypt);
        //Assertions.fail("this should fail");
        log.info(decrypt);
    }

    @BeforeEach
    void beforeEach() {
        log.info("beforeEach");
    }

    @AfterEach
    void afterEach() {
        log.info("afterEach");
    }

    @BeforeAll
    static void beforeAll() {
        log.info("beforeAll");
    }

    @AfterAll
    static void afterAll() {
        log.info("afterAll");
    }
}
