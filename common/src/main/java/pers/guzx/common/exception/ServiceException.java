package pers.guzx.common.exception;


import pers.guzx.common.enums.CommonEnum;

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

    public ServiceException(CommonEnum CommonEnum, String errorMsg) {
        super(CommonEnum, errorMsg);
    }

    public ServiceException(CommonEnum CommonEnum, Exception exception) {
        super(CommonEnum, exception);
    }

    public ServiceException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }
}
