package pers.guzx.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.model.Result;
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
public class Demo {

    @Value("${spring.application.name}")
    private String applicationName;

    @Resource
    private ConfigProperties configProperties;

    @GetMapping("conn")
    public String testConn() {
        return applicationName;
    }

    @GetMapping("/commResp1")
    public Result<String> testCommonResp1() {
        String name = configProperties.getName();
        log.info("配置文件中属性为：{}", name);
        return Result.succeed(name);
    }

    @GetMapping("/commResp2")
    public Result<String> testCommonResp2() {
        return Result.succeed("success message");
    }

    @GetMapping("/commResp3")
    public Result<String> testCommonResp3() {
        return Result.succeed("Data", "success message");
    }

    @GetMapping("/commResp4")
    public Result<String> testCommonResp4() {
        return Result.succeed("Data");
    }
}
