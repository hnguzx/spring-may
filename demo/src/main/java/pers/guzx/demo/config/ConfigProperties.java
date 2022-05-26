package pers.guzx.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Getter
@Setter
@Component
public class ConfigProperties {

//    @Value("${base.address}")
//    private String address;

    @Value("${base.timeout}")
    private String timeout;

}
