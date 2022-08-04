package pers.guzx.common.exception;

import lombok.Data;
import pers.guzx.common.enums.Code;

/**
 * @author 25446
 */
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

    public BaseException(Code code, Exception exception) {
        super(code.getMsg(), exception);
        this.errorMessage = code.getMsg();
        this.errorCode = code;
    }

    public BaseException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
        this.errorMessage = errorMessage;
        this.errorCode = Code.ERROR;
    }
}
