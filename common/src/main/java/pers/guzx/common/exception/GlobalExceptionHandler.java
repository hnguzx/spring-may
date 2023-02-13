package pers.guzx.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.enums.Code;

/**
 * 统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 参数校验异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> validException(MethodArgumentNotValidException exception) {
        log.error("parameter error:{}", exception);
        FieldError fieldError = exception.getFieldError();
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        return Result.failed(Code.PARAMETER_ERROR, "field: " + field + " input error," + defaultMessage);
    }

    // 参数校验异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ValidException.class)
    public Result<String> validException(RuntimeException exception) {
        log.error("parameter error:{}", exception);
        return Result.failed();
    }

    // 基本异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BaseException.class)
    public Result<String> baseException(BaseException exception) {
        log.error("system error:{}", exception);
        return Result.failed(exception.getErrorCode(), exception.getErrorMessage());
    }

    // 未知异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Result<String> baseException(RuntimeException exception) {
        log.error("unknown error:{}", exception);
        return Result.failed();
    }
}
