package pers.guzx.common.exception;

import lombok.Data;
import pers.guzx.common.enums.Code;

@Data
public class BaseException extends RuntimeException {
    private Code errorCode;
    private String errorMessage;
    private Throwable throwable;

    public BaseException() {
        super();
        this.errorCode = Code.ERROR;
        this.errorMessage = "系统异常";
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = Code.ERROR;
    }

    public BaseException(Code errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public BaseException(Code code, Throwable throwable) {
        super(code.getMsg(), throwable);
        this.errorMessage = code.getMsg();
        this.errorCode = code;
    }

    public BaseException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorMessage = errorMessage;
        this.errorCode = Code.ERROR;
    }

    public BaseException(Code errorCode, String errorMessage, Throwable throwable) {
        super(errorCode.getMsg(),throwable);
        this.errorCode = errorCode;
        this.throwable = throwable;
    }
}
