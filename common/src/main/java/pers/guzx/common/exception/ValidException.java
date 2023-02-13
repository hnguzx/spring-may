package pers.guzx.common.exception;

import lombok.NoArgsConstructor;
import pers.guzx.common.enums.Code;

public class ValidException extends BaseException {
    public ValidException() {
        super();
    }

    public ValidException(String errorMessage) {
        super(errorMessage);
    }

    public ValidException(Code errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public ValidException(Code code, Throwable throwable) {
        super(code.getMsg(), throwable);
    }

    public ValidException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public ValidException(Code errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
    }
}
