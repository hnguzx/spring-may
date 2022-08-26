package pers.guzx.user.common;

import lombok.Data;
import lombok.Getter;

/**
 * @author 25446
 */
@Getter
public enum LoginType {

    USER_NAME_AND_PASSWORD(1, "用户名密码登录"),
    MOBILE_AND_CODE(2, "用户名密码登录"),
    EMAIL_AND_CODE(3, "用户名密码登录");

    private Integer code;
    private String description;

    LoginType(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
