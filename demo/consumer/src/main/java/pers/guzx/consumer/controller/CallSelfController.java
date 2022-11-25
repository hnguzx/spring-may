package pers.guzx.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.entity.Result;

@RestController
public class CallSelfController {

    @GetMapping("/call/self")
    public Result<String> call() {
        int i = 1 / 0;
        return Result.succeed();
    }
}
