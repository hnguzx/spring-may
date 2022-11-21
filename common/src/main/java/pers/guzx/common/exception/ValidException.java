package pers.guzx.common.exception;

import pers.guzx.enums.CommonEnum;

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

    public ValidException(CommonEnum CommonEnum, String errorMsg) {
        super(CommonEnum, errorMsg);
    }

    public ValidException(CommonEnum CommonEnum, Exception exception) {
        super(CommonEnum, exception);
    }

    public ValidException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
