package pers.guzx.api.demo.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.guzx.common.entity.Result;
import pers.guzx.fallback.demo.consumer.CallOtherFallback;

@FeignClient(value = "consumer-server", fallbackFactory = CallOtherFallback.class)
public interface CallOtherApi {
    @GetMapping("/call/producer/client/global/fallback")
    Result<String> callProducerClientGlobalFallback();

    @GetMapping("/call/producer/client/fallback")
    Result<String> callProducerClientFallback();

    @GetMapping("/call/producer/server/fallback")
    Result<String> callProducerServerFallback();

    @GetMapping("/call/producer/global/fallback")
    Result<String> callProducerGlobalFallback();


    @GetMapping("/call/producer/circuit")
    Result<String> callProducerCircuit(@RequestParam("id") Integer id);


}
