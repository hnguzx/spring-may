package pers.guzx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.guzx.enums.CommonEnum;
import pers.guzx.enums.SystemCode;

import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data = null;
    private Object code;
    private String message;

    public static <D> Result<D> succeed() {
        return buildResult(null, SystemCode.SUCCESS.getValueObject(), null);
    }

    public static <D> Result<D> succeed(D model, String msg) {
        return buildResult(model, SystemCode.SUCCESS.getValueObject(), msg);
    }

    public static <D> Result<D> succeed(D model) {
        return buildResult(model, SystemCode.SUCCESS.getValueObject(), "");
    }

    public static <D> Result<D> failed() {
        return buildResult(null, SystemCode.INTERNAL_SERVER_ERROR.getValueObject(), null);
    }

    public static <D> Result<D> failed(D model) {
        return buildResult(model, SystemCode.INTERNAL_SERVER_ERROR.getValueObject(), SystemCode.INTERNAL_SERVER_ERROR.getDescription());
    }

    public static <D> Result<D> failed(D model, String msg) {
        return buildResult(model, SystemCode.INTERNAL_SERVER_ERROR.getValueObject(), msg);
    }

    public static <D> Result<D> failed(D model, CommonEnum code) {
        return buildResult(model, code.getValueObject(), code.getDescription());
    }

    public static <D> Result<D> failed(String msg, CommonEnum code) {
        return buildResult(null, code.getValueObject(), msg);
    }

    public static <D> Result<D> failed(D model, String msg, CommonEnum code) {
        return buildResult(model, code.getValueObject(), msg);
    }

    public static <D> Result<D> failed(CommonEnum code) {
        return buildResult(null, code.getValueObject(), code.getDescription());
    }

    public static <D> Result<D> buildResult(D data, Object code, String msg) {
        return new Result<D>(data, code, msg);
    }
}
