package pers.guzx.api.demo.producer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.guzx.common.entity.Result;
import pers.guzx.fallback.demo.producer.ExceptionFallback;

@FeignClient(value = "producer-server", fallbackFactory = ExceptionFallback.class)
public interface ExceptionApi {

    @GetMapping("/exception/common")
    Result commonException();

    @GetMapping("/exception/base")
    Result baseException();

    @GetMapping("/exception/circuit")
    Result circuitException(@RequestParam("id") Integer id);
}
