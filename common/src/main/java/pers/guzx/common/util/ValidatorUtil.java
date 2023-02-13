package pers.guzx.common.util;

import org.apache.commons.lang3.StringUtils;
import pers.guzx.common.exception.ValidException;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/17 15:30
 * @describe 数据校验
 */
public class ValidatorUtil {
    /**
     * 字符串空值校验
     *
     * @param value
     * @param errorMessage
     * @throws ValidException
     */
    public static void requireNotBlank(String value, String errorMessage) throws ValidException {
        if (StringUtils.isBlank(value)) {
            throw errorMessage != null ? new ValidException(errorMessage) : new ValidException();
        }
    }

    /**
     * 对象null校验
     *
     * @param value
     * @param errorMessage
     * @throws ValidException
     */
    public static void requireNotNull(Object value, String errorMessage) throws ValidException {
        if (value == null) {
            throw errorMessage != null ? new ValidException(errorMessage) : new ValidException();
        }
    }
}
