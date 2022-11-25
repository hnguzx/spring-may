package pers.guzx.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.api.demo.producer.ExceptionApi;
import pers.guzx.common.annotation.SysLog;
import pers.guzx.common.exception.BaseException;
import pers.guzx.entity.Result;

/**
 * @author 25446
 */
@Slf4j
@RestController
public class ExceptionController implements ExceptionApi {

    @SysLog
    @Override
    public Result commonException() {
        Integer.parseInt("asd123");
        return Result.succeed();
    }

    @Override
    public Result baseException() {
        throw new BaseException("错误信息");
    }

    @Override
    public Result<String> circuitException(Integer id) {
        log.info("进入实际方法");
        int i = 1 / id;
        return Result.succeed();
    }
}
