package pers.guzx.api.demo.producer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pers.guzx.entity.Result;

@FeignClient
public interface ExceptionApi {

    @GetMapping("/common")
    public Result commonException();

    @GetMapping("/base")
    public Result baseException();
}
