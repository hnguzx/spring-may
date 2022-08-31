package pers.guzx.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.entity.dto.Result;

/**
 * @author 25446
 */
@RestController
@RequestMapping("/private")
public class SpecialController {

    @GetMapping("/test")
    public Result<String> test(){
        return Result.succeed();
    }
}
