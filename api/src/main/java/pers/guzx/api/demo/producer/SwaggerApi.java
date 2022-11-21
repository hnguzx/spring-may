package pers.guzx.api.demo.producer;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("producer-server")
public interface SwaggerApi {
}
