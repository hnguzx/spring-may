package pers.guzx.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.entity.dto.Result;

@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/session/expired")
    public Result<String> getdemo() {
        return Result.succeed("session expired!");
    }
}
