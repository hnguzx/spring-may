package pers.guzx.common.entity.dto;

import lombok.*;
import pers.guzx.common.enums.Language;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/21 16:25
 * @describe
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequest<DataField> {

    /**
     * 语言（用于资源国际化）
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Language lang;

    public String getLang() {
        return lang != null ? lang.getValue() : null;
    }

    public void setLang(String lang) {
        this.lang = Language.fromValue(lang);
    }

    public Language getLangEnum() {
        return lang;
    }

    public void setLangEnum(Language lang) {
        this.lang = lang;
    }

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户类型
     */
//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    private UserType userType;

//    public Integer getUserType() {
//        return userType != null ? userType.getValue() : null;
//    }

//    public void setUserType(Integer userType) {
//        this.userType = UserType.fromValue(userType);
//    }

//    public UserType getUserTypeEnum() {
//        return userType;
//    }

//    public void setUserTypeEnum(UserType userType) {
//        this.userType = userType;
//    }

    /**
     * 用户名
     */
    private String loginUserName;
    /**
     * 请求来源IP
     */
    private String ip;
    /**
     * 版本號
     */
    private long pkgVersionCode;
    /**
     * 版本名稱
     */
    private String pkgVersionName;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备系统（android/ios）
     */
    private String deviceOs;
    /**
     * 设备系统版本号
     */
    private String deviceVersion;
    /**
     * 设备型号（MIX 2S/IPhone 11）
     */
    private String deviceModel;
    /**
     * AdvertisingId
     */
    private String advertisingId;
    /**
     * 设备类型,android or ios
     */
    private String clientType;
    /**
     * 时区
     */
    private String timezone;
    /**
     * 是否验签
     */
    private boolean checkSign;
    /**
     * ServiceId
     */
    private String serviceId;
    /**
     * ServiceToken
     */
    private String serviceToken;
    /**
     * register_token
     */
    private String registerToken;
    /**
     * 主题
     */
    private String theme;
    /**
     *
     */
    private DataField data;

    @Override
    public String toString() {
        return "CommonRequest{" +
                "lang=" + lang +
                ", userId='" + userId + '\'' +
                ", loginUserName='" + loginUserName + '\'' +
                ", ip='" + ip + '\'' +
                ", pkgVersionCode=" + pkgVersionCode +
                ", pkgVersionName='" + pkgVersionName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceOs='" + deviceOs + '\'' +
                ", deviceVersion='" + deviceVersion + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", advertisingId='" + advertisingId + '\'' +
                ", clientType='" + clientType + '\'' +
                ", timezone='" + timezone + '\'' +
                ", checkSign=" + checkSign +
                ", serviceId='" + serviceId + '\'' +
                ", serviceToken='" + serviceToken + '\'' +
                ", registerToken='" + registerToken + '\'' +
                ", theme='" + theme + '\'' +
                ", data=" + data +
                '}';
    }
}
