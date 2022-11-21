package pers.guzx.user.controller;

import pers.guzx.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 25446
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/test")
    public Result<String> test(){
        return Result.succeed();
    }

    @GetMapping("/session/expired")
    public Result<String> sessionExpired() {
        return Result.succeed("session expired!");
    }
}
