package pers.guzx.api.demo.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pers.guzx.entity.Result;
import pers.guzx.fallback.demo.consumer.CallSelfFallback;

@FeignClient(value = "consumer-server", fallbackFactory = CallSelfFallback.class)
public interface CallSelfApi {

    @GetMapping("/call/self")
    public Result<String> call();
}
