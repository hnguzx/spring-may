package pers.guzx.producer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author 25446
 * 测试读取配置中心配置
 */
@Getter
@Setter
@Component
public class ConfigProperties {

    @Value("${base.timeout}")
    private String timeout;

}
