package pers.guzx.demo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
@ConfigurationProperties(prefix = "base")
public class ConfigProperties {
    private String name;
}
