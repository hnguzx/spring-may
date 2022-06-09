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

    // 参数校验错误
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result<String> validException(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        FieldError fieldError = exception.getFieldError();
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        return Result.failed(Code.PARAMETER_ERROR, "field: " + field + " input error," + defaultMessage);
    }

    // 基本异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BaseException.class)
    public Result<String> baseException(BaseException exception) {
        exception.printStackTrace();
        return Result.failed(exception.getErrorCode(),exception.getErrorMessage());
    }

    // 未知异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Result<String> baseException(RuntimeException exception) {
        exception.printStackTrace();
        return Result.failed();
    }
}
