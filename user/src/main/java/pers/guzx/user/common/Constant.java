package pers.guzx.user.common;

/**
 * @author 25446
 */
public class Constant {

    public static final String LOGIN_URL = "/common/login";
    public static final String LOGOUT_URL = "/common/logout";
    public static final String REMEMBER_ME_PARAMETER = "remember";
    public static final String REMEMBER_ME_KEY = "rememberMeKey";

    public static final String LOGIN_USER_NAME = "username";
    public static final String LOGIN_PASSWORD = "password";
    public static final String LOGIN_MOBILE = "mobile";
    public static final String LOGIN_EMAIL = "email";
    public static final String CODE = "code";
    public static final String LOGIN_TYPE = "type";
    public static final Integer MAX_REMEMBER_TIME = 60;
    public static final Integer MAX_SESSIONS = 1;

    public static final String INVALID_SESSION_URL = "/common/session/expired";

    public static final String COMMON_REQUEST_PREFIX = "common";
}
