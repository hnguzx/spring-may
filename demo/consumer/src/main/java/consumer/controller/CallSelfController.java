package consumer.controller;

import pers.guzx.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallSelfController {

    @GetMapping("/call/self")
    public Result<String> call() {
        return Result.succeed();
    }
}
