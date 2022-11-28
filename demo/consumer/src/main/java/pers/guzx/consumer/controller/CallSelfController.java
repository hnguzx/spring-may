package pers.guzx.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.entity.Result;

@RestController
public class CallSelfController {

    @GetMapping("/call/self")
    public Result<String> call() {
//        int i = 1 / 0;
        return Result.succeed();
    }

    @GetMapping("/call/params1")
    public Result<String> callParameter1(@RequestParam("key") String name) {
        return Result.succeed(name);
    }

    @GetMapping("/call/params2")
    public Result<String> callParameter2(@RequestParam("key") String name, @RequestParam("value") String value) {
        return Result.succeed(name + value);
    }
}
