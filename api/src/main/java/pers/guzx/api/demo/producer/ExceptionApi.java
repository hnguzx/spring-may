package pers.guzx.api.demo.producer;

import pers.guzx.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("producer-server")
public interface ExceptionApi {

    @GetMapping("/common")
    public Result commonException();

    @GetMapping("/base")
    public Result baseException();
}
