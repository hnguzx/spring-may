package pers.guzx.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.api.demo.consumer.CallOtherApi;
import pers.guzx.api.demo.producer.DemoApi;
import pers.guzx.api.demo.producer.ExceptionApi;
import pers.guzx.common.entity.Result;

@DefaultProperties(defaultFallback = "globalFallbackMethod")
@Slf4j
@RestController
public class CallOtherController implements CallOtherApi {

    @Autowired
    private DemoApi demoApi;

    @Autowired
    private ExceptionApi exceptionApi;

    /**
     * 客户端全局降级(本类中)
     *
     * @return
     */
    @HystrixCommand
    public Result<String> callProducerClientGlobalFallback() {
        Result<String> demoResult = demoApi.successResp1();
        return Result.succeed("callProducerSuccess " + demoResult.getMessage());
    }

    /**
     * 客户端单独降级
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public Result<String> callProducerClientFallback() {
        Result<String> demoResult = demoApi.errorResp2();
        return Result.succeed("callProducerError " + demoResult.getMessage());
    }

    /**
     * 服务端单独降级
     *
     * @return
     */
    public Result<String> callProducerServerFallback() {
        Result<String> result = demoApi.errorResp1("guzx");
        return Result.succeed(result.getMessage());
    }

    /**
     * 服务端全局降级
     *
     * @return
     */
    @Override
    public Result<String> callProducerGlobalFallback() {
        String result = demoApi.testConn();
        return Result.succeed("success" + result);
    }

    @Override
    public Result<String> callProducerCircuit(Integer id) {
        Result result = exceptionApi.circuitException(id);
        return result;
    }


    public Result<String> fallbackMethod() {
        log.info("client fallback method");
        return Result.failed();
    }

    public Result<String> globalFallbackMethod() {
        log.info("全局降级方法！");
        return Result.failed();
    }
}
