package pers.guzx.common.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.guzx.common.enums.Code;

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
    private Integer code;
    private String message;

    public static <D> Result<D> succeed() {
        return succeedWith(null, Code.SUCCESS.getCode(), null);
    }

    public static <D> Result<D> succeed(D model, String msg) {
        return succeedWith(model, Code.SUCCESS.getCode(), msg);
    }

    public static <D> Result<D> succeed(D model) {
        return succeedWith(model, Code.SUCCESS.getCode(), "");
    }

    public static <D> Result<D> succeedWith(D data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }


    public static <D> Result<D> failed() {
        return failedWith(null, Code.ERROR.getCode(), null);
    }

    public static <D> Result<D> failed(D model) {
        return failedWith(model, Code.ERROR.getCode(), Code.ERROR.getMsg());
    }

    public static <D> Result<D> failed(D model, String msg) {
        return failedWith(model, Code.ERROR.getCode(), msg);
    }

    public static <D> Result<D> failed(D model, Code code) {
        return failedWith(model, code.getCode(), code.getMsg());
    }

    public static <D> Result<D> failed(Code code, String msg) {
        return failedWith(null, code.getCode(), msg);
    }

    public static <D> Result<D> failedWith(D data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }
}
