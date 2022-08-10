package pers.guzx.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.enums.Code;
import pers.guzx.demo.config.ConfigProperties;

import javax.annotation.Resource;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@RefreshScope
@Slf4j
@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private ConfigProperties configProperties;

    @Value("${test.name}")
    private String test;

    @GetMapping("conn")
    public String testConn() {
        return test;
    }

    @GetMapping("/successResp1")
    public Result<String> successResp1() {
        String address = configProperties.getTimeout();
        log.info("配置文件中属性为：{}", address);
        return Result.succeed(address);
    }

    @GetMapping("/successResp2")
    public Result<String> successResp2(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
        String result = "name=" + name + ", age=" + age;
        log.info("result:{}", result);
        return Result.succeed(result, "success message");
    }

    @GetMapping("/errorResp1")
    public Result<String> errorResp1(@RequestParam(value = "name") String name) {
        log.info("name:{}", name);
        return Result.failed(Code.PARAMETER_ERROR, "error message");
    }

    @GetMapping("/errorResp2")
    public Result<String> errorResp2() {
        return Result.failedWith("error data", 405, "error message");
    }
}
