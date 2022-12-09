package pers.guzx.common.exception;

import lombok.Data;
import pers.guzx.common.enums.CommonEnum;
import pers.guzx.common.enums.SystemCode;

/**
 * @author 25446
 */
@Data
public class BaseException extends RuntimeException {
    private CommonEnum errorCode;
    private String errorMessage;
    private Throwable throwable;

    public BaseException() {
        super();
        this.errorCode = SystemCode.INTERNAL_SERVER_ERROR;
        this.errorMessage = "系统异常";
    }

    public BaseException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = SystemCode.INTERNAL_SERVER_ERROR;
    }

    public BaseException(CommonEnum errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public BaseException(CommonEnum code, Exception exception) {
        super(code.getDetailMessage(), exception);
        this.errorMessage = code.getDetailMessage();
        this.errorCode = code;
    }

    public BaseException(String errorMessage, Exception exception) {
        super(errorMessage, exception);
        this.errorMessage = errorMessage;
        this.errorCode = SystemCode.INTERNAL_SERVER_ERROR;
    }
}
