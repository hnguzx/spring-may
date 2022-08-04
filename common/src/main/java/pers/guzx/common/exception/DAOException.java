package pers.guzx.common.exception;

import pers.guzx.common.enums.Code;

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

    public DAOException(Code code, String errorMsg) {
        super(code, errorMsg);
    }

    public DAOException(Code code, Exception exception) {
        super(code, exception);
    }

    public DAOException(String errorMsg, Exception exception) {
        super(errorMsg, exception);
    }

}
