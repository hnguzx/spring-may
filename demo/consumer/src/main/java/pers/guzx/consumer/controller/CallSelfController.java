package pers.guzx.consumer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pers.guzx.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;

@Slf4j
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

    @PostMapping("/parameters")
    public Result testParameter(@RequestBody Page page, @RequestBody CountryVO countryVO) {
        log.info("page:{},country:{}", page, countryVO);
        return Result.succeed();
    }
}
