package consumer.controller;

import pers.guzx.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.api.demo.producer.DemoApi;

@RestController
public class CallOtherController {

    @Autowired
    private DemoApi demoApi;

    @GetMapping("/call/producer")
    public Result<String> callProducer() {
        Result<String> demoResult = demoApi.successResp1();
        return Result.succeed("consumer" + demoResult.getData());
    }
}
