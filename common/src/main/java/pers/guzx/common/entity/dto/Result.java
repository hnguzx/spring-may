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
//@ApiModel(value = "Result", description = "公共返回")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

//    @ApiModelProperty(value = "返回数据", name = "data")
    private T data = null;
//    @ApiModelProperty(value = "返回状态码", name = "code", example = "200")
    private Integer code;
//    @ApiModelProperty(value = "返回信息", name = "message", example = "SUCCESS")
    private String message;

    public static <T> Result<T> succeed() {
        return succeedWith(null, Code.SUCCESS.getCode(), null);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return succeedWith(model, Code.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return succeedWith(model, Code.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> succeedWith(T data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }


    public static <T> Result<T> failed() {
        return failedWith(null, Code.ERROR.getCode(), null);
    }

    public static <T> Result<T> failed(T model) {
        return failedWith(model, Code.ERROR.getCode(), Code.ERROR.getMsg());
    }

    public static <T> Result<T> failed(T model, String msg) {
        return failedWith(model, Code.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failedWith(T data, Integer code, String msg) {
        return new Result<>(data, code, msg);
    }
}
