package pers.guzx.api.demo.producer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pers.guzx.common.entity.Result;
import pers.guzx.fallback.demo.producer.DemoFallback;

//@FeignClient(value = "producer-server",fallbackFactory = DemoFallback.class)
@FeignClient(value = "producer-server")
public interface DemoApi {

    @GetMapping({"/conn"})
    String testConn();

    @GetMapping({"/successResp1"})
    Result<String> successResp1();

    @GetMapping({"/successResp2"})
    Result<String> successResp2(@RequestParam("name") String name, @RequestParam("age") String age);

    @GetMapping({"/errorResp1"})
    Result<String> errorResp1(@RequestParam("name") String name);

    @GetMapping({"/errorResp2"})
    Result<String> errorResp2();
}
