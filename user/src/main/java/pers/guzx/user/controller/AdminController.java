package pers.guzx.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.entity.Result;

/**
 * @author 25446
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/test")
    public Result<String> test(){
        return Result.succeed();
    }
}
