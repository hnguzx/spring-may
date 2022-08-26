package pers.guzx.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 25446
 */
@MapperScan("pers.guzx.user.mapper")
@SpringBootApplication(scanBasePackages = {"pers.guzx"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
