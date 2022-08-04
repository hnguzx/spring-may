package pers.guzx.common.exception;

import pers.guzx.common.enums.Code;

/**
 * @author 25446
 */
public class ValidException extends BaseException{
    public ValidException() {
        super();
    }

    public ValidException(String errorMsg) {
        super(errorMsg);
    }

    public ValidException(Code code, String errorMsg) {
        super(code, errorMsg);
    }

    public ValidException(Code code, Exception exception) {
        super(code, exception);
    }

    public ValidException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
