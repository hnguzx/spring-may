//package pers.guzx.common.handler;
//
//import pers.guzx.common.exception.BaseException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import pers.guzx.entity.Result;
//import pers.guzx.enums.CommonRespCode;
//
///**
// * 统一异常处理
// * 服务降级的一种处理
// * @author 25446
// */
//@Slf4j
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    /**
//     * 参数校验错误
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result<String> validException(MethodArgumentNotValidException exception) {
//        log.error("valid exception");
//        exception.printStackTrace();
//        FieldError fieldError = exception.getFieldError();
//        String field = fieldError.getField();
//        String defaultMessage = fieldError.getDefaultMessage();
//        return Result.failed("field: " + field + " input error," + defaultMessage,CommonRespCode.DATA_VALIDATE);
//    }
//
//    /**
//     * 基本异常
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(value = BaseException.class)
//    public Result<String> baseException(BaseException exception) {
//        log.error("base exception");
//        exception.printStackTrace();
//        return Result.failed(exception.getErrorMessage(),exception.getErrorCode());
//    }
//
//    /**
//     * 未知异常
//     * @param exception
//     * @return
//     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(value = Exception.class)
//    public Result<String> baseException(RuntimeException exception) {
//        log.error("unknown exception");
//        exception.printStackTrace();
//        return Result.failed();
//    }
//}
