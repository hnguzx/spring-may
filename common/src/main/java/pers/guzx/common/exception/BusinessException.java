package pers.guzx.common.exception;

import pers.guzx.common.enums.Code;

/**
 * @author 25446
 */
public class BusinessException extends BaseException {

    public BusinessException() {
        super();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
    }

    public BusinessException(Code code, String errorMsg) {
        super(code, errorMsg);
    }

    public BusinessException(Code code, Exception exception) {
        super(code, exception);
    }

    public BusinessException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
