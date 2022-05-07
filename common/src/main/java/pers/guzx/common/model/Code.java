package pers.guzx.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@AllArgsConstructor
@Getter
public enum Code {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR");

    private Integer code;
    private String msg;
}
