package pers.guzx.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.model.Result;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@RestController
public class Demo {

    @GetMapping("conn")
    public String testConn(){
        return "success";
    }

    @GetMapping("/commResp1")
    public Result<String> testCommonResp1(){
        return Result.succeed();
    }

    @GetMapping("/commResp2")
    public Result<String> testCommonResp2(){
        return Result.succeed("success message");
    }

    @GetMapping("/commResp3")
    public Result<String> testCommonResp3(){
        return Result.succeed("Data","success message");
    }

    @GetMapping("/commResp4")
    public Result<String> testCommonResp4(){
        return Result.succeed("Data");
    }
}
