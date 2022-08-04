package pers.guzx.common.exception;

import pers.guzx.common.enums.Code;

/**
 * @author 25446
 */
public class ServiceException extends BaseException {
    public ServiceException() {
        super();
    }

    public ServiceException(String errorMsg) {
        super(errorMsg);
    }

    public ServiceException(Code code, String errorMsg) {
        super(code, errorMsg);
    }

    public ServiceException(Code code, Exception exception) {
        super(code, exception);
    }

    public ServiceException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
