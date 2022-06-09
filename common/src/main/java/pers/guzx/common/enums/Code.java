package pers.guzx.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@AllArgsConstructor
@Getter
public enum Code {
    SUCCESS(200,"OK"),
    PARAMETER_ERROR(400,"Bad Request"),
    ERROR(500,"Internal Server Error");

    private Integer code;
    private String msg;
}
