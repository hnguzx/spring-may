package pers.guzx.fallback.demo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import pers.guzx.api.demo.producer.DemoApi;
import pers.guzx.common.entity.Result;

@Slf4j
@Component
public class DemoFallback implements DemoApi {

    @Override
    public String testConn() {
        return "触发回退";
    }

    @Override
    public Result<String> successResp1() {
        return Result.failed("触发回退");
    }

    @Override
    public Result<String> successResp2(String name, String age) {
        return Result.failed("触发回退");
    }

    @Override
    public Result<String> errorResp1(String name) {
        return Result.failed("触发回退");
    }

    @Override
    public Result<String> errorResp2() {
        return Result.failed("触发回退");
    }
}
