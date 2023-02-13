package pers.guzx.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.guzx.common.enums.CommonEnum;
import pers.guzx.common.enums.SystemCode;

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
@ApiModel(description = "返回信息")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("具体数据")
    private T data = null;
    @ApiModelProperty("业务状态码")
    private CommonEnum code;
    @ApiModelProperty("描述信息")
    private String message;

    public static <D> Result<D> succeed() {
        return buildResult(null, SystemCode.SUCCESS, null);
    }

    public static <D> Result<D> succeed(D model, String msg) {
        return buildResult(model, SystemCode.SUCCESS, msg);
    }

    public static <D> Result<D> succeed(D model) {
        return buildResult(model, SystemCode.SUCCESS, "");
    }

    public static <D> Result<D> failed() {
        return buildResult(null, SystemCode.INTERNAL_SERVER_ERROR, null);
    }

    public static <D> Result<D> failed(D model) {
        return buildResult(model, SystemCode.INTERNAL_SERVER_ERROR, SystemCode.INTERNAL_SERVER_ERROR.getDescription());
    }

    public static <D> Result<D> failed(D model, String msg) {
        return buildResult(model, SystemCode.INTERNAL_SERVER_ERROR, msg);
    }

    public static <D> Result<D> failed(D model, CommonEnum code) {
        return buildResult(model, code, code.getDescription());
    }

    public static <D> Result<D> failed(String msg, CommonEnum code) {
        return buildResult(null, code, msg);
    }

    public static <D> Result<D> failed(D model, String msg, CommonEnum code) {
        return buildResult(model, code, msg);
    }

    public static <D> Result<D> failed(CommonEnum code) {
        return buildResult(null, code, code.getDescription());
    }

    public static <D> Result<D> buildResult(D data, CommonEnum code, String msg) {
        return new Result<D>(data, code, msg);
    }
}
