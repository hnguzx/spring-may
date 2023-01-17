package pers.guzx.fallback.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.guzx.api.demo.consumer.CallOtherApi;
import pers.guzx.common.entity.Result;

@Slf4j
@Component
public class CallOtherFallback implements FallbackFactory<CallOtherApi> {
    @Override
    public CallOtherApi create(Throwable cause) {
        log.error("callOther fallback factory触发，异常原因：{}",cause.getMessage(),cause);
        return new CallOtherApi() {
            @Override
            public Result<String> callProducerClientGlobalFallback() {
                return null;
            }

            @Override
            public Result<String> callProducerClientFallback() {
                return null;
            }

            @Override
            public Result<String> callProducerServerFallback() {
                return null;
            }

            @Override
            public Result<String> callProducerGlobalFallback() {
                return null;
            }

            @Override
            public Result<String> callProducerCircuit(Integer id) {
                return null;
            }
        };
    }
}
