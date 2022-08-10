package pers.guzx.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guzx
 * @version 1.0
 * @date 2022/6/8 16:51
 * @describe 日期工具类
 */
public class DateUtils {
    public static String getDateFormatString(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
