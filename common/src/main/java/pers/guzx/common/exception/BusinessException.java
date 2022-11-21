package pers.guzx.common.exception;

import pers.guzx.enums.CommonEnum;

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

    public BusinessException(CommonEnum CommonEnum, String errorMsg) {
        super(CommonEnum, errorMsg);
    }

    public BusinessException(CommonEnum CommonEnum, Exception exception) {
        super(CommonEnum, exception);
    }

    public BusinessException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
