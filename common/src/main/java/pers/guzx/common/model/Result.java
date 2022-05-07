package pers.guzx.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private T data = null;
    private Integer code;
    private String message;

    public static <T> Result<T> succeed() {
        return succeedWith(null, Code.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> succeed(String msg) {
        return succeedWith(null, Code.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, Code.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, Code.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> succeedWith(T datas, Integer code, String msg) {
        return new Result<>(datas, code, msg);
    }


    public static <T> Result<T> failed() {
        return failedWith(null, Code.ERROR.getCode(), null);
    }

    public static <T> Result<T> failed(String msg) {
        return failedWith(null, Code.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, Code.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }
}
