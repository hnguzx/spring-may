package pers.guzx.producer;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 25446
 */
@EnableHystrix
@EnableFeignClients(basePackages = {"pers.guzx.api"})
@EnableCaching
@EnableApolloConfig
@EnableEurekaClient
@MapperScan("pers.guzx.producer.mapper")
@SpringBootApplication(scanBasePackages = {"pers.guzx"})
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
