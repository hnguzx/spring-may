package pers.guzx.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.annotation.SysLog;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.exception.BaseException;

/**
 * @author 25446
 */
@RestController
@RequestMapping("/exception")
public class Exception extends Throwable {

    @SysLog
    @GetMapping("/common")
    public Result commonException() {
        Integer.parseInt("asd123");
        return Result.succeed();
    }

    @GetMapping("/base")
    public Result baseException() {
        throw new BaseException("错误信息");
    }
}
