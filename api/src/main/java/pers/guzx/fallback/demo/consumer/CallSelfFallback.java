package pers.guzx.fallback.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.guzx.api.demo.consumer.CallSelfApi;
import pers.guzx.common.entity.Result;

@Slf4j
@Component
public class CallSelfFallback implements FallbackFactory<CallSelfApi> {
    @Override
    public CallSelfApi create(Throwable cause) {
        return new CallSelfApi() {
            @Override
            public Result<String> call() {
                return Result.failed("客户端全局降级");
            }
        };
    }
}
