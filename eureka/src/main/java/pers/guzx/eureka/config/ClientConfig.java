package pers.guzx.eureka.config;

import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClientConfig extends EurekaClientConfigBean {

    /**
     * 从数据库中读取eureka-server的配置
     * @param myZone the zone in which the instance is deployed.
     *
     * @return
     */
    @Override
    public List<String> getEurekaServerServiceUrls(String myZone) {
        return super.getEurekaServerServiceUrls(myZone);
    }
}
