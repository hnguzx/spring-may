package pers.guzx.demo;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pers.guzx.common.config.EncryptionConfig;

//@Import(EncryptionConfig.class)
@MapperScan("pers.guzx.demo.mapper")
@SpringBootApplication(scanBasePackages = {"pers.guzx"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
