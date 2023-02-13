package pers.guzx.common.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/21 16:26
 * @describe
 */
public enum Language {
    ENGLISH_US("en_US", "", null),
    CHINESE_SIMPLIFIED("zh_CN", "", null),
    CHINESE_TRADITIONAL_HONGKONG("zh_HK", "", null);

    String value;
    String description;
    Map<String, String> msgTemplate;



    Language(String value, String description, Map<String, String> msgTemplate) {
        this.value = value;
        this.description = description;
        this.msgTemplate = msgTemplate;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    public String value() {
        return this.value;
    }

    /**
     * @deprecated
     */
    public static Language fromString(String value) {
        return fromValue(value);
    }

    public static Language fromValue(String value) {
        if (StringUtils.isBlank(value)) { return null; }
        Language[] array = Language.values();
        for (Language object : array) {
            if (object.value.equalsIgnoreCase(value)) {
                return object;
            }
        }

        throw new IllegalArgumentException("illegal enum value: " + value);
    }

    public String getValue() {
        return this.value;
    }

    public Object getValueObject() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMsgTemplate(String lang) {
        if (msgTemplate == null) {
            return this.description;
        }
        String t = msgTemplate.get(lang);
        if (t == null) {
            t = msgTemplate.get("en_US");
        }
        return t;
    }
}
