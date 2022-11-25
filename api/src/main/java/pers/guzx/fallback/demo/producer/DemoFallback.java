package pers.guzx.fallback.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.guzx.api.demo.producer.DemoApi;
import pers.guzx.entity.Result;

@Slf4j
@Component
public class DemoFallback implements FallbackFactory<DemoApi> {
    @Override
    public DemoApi create(Throwable cause) {
        log.error("demo fallback factory触发，异常原因：{}",cause.getMessage(),cause);
        return new DemoApi() {
            @Override
            public String testConn() {
                return null;
            }

            @Override
            public Result<String> successResp1() {
                return null;
            }

            @Override
            public Result<String> successResp2(String name, String age) {
                return null;
            }

            @Override
            public Result<String> errorResp1(String name) {
                return null;
            }

            @Override
            public Result<String> errorResp2() {
                return null;
            }
        };
    }
}
