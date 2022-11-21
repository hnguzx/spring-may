package pers.guzx.common.exception;

import pers.guzx.enums.CommonEnum;

/**
 * @author 25446
 */
public class DAOException extends BaseException {

    public DAOException() {
        super();
    }

    public DAOException(String errorMsg) {
        super(errorMsg);
    }

    public DAOException(CommonEnum CommonEnum, String errorMsg) {
        super(CommonEnum, errorMsg);
    }

    public DAOException(CommonEnum CommonEnum, Exception exception) {
        super(CommonEnum, exception);
    }

    public DAOException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }

}
