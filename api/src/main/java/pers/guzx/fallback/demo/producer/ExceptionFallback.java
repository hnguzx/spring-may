package pers.guzx.fallback.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.guzx.api.demo.producer.ExceptionApi;
import pers.guzx.entity.Result;

@Slf4j
@Component
public class ExceptionFallback implements FallbackFactory<ExceptionApi> {
    @Override
    public ExceptionApi create(Throwable cause) {
        return new ExceptionApi() {
            @Override
            public Result commonException() {
                return null;
            }

            @Override
            public Result baseException() {
                return null;
            }

            @Override
            public Result circuitException(Integer id) {
                log.info("触发降级");
                return Result.failed();
            }
        };
    }
}
