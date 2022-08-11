package pers.guzx.demo.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pers.guzx.common.enums.Code;

import java.util.List;

/**
 * 参数化测试
 */
@Slf4j
public class ParameterTest {

    /**
     * 有几个参数测试方法就会执行几次
     * 单类型数据源
     *
     * @param value
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 4, 5, 6, 7, 8, 9})
    void testValue(int value) {
        log.info("testValue:{}", value);
    }

    /**
     * 多个不同参数，类型需要与方法声明的类型保持一致
     * 参数可以为空，但是需要用,占位
     *
     * @param key
     * @param value
     */
    @ParameterizedTest
    @CsvSource(value = {"key1,1", "key2,2", "key3,", "key4,4", "key5,5"})
    void testCsv(String key, Integer value) {
        log.info("key:{},value:{}", key, value);
    }

    @ParameterizedTest
    @CsvSource(value = {"key1,1,value", "key2,2,value2", "key3,3,value3", "key4,4,value4", "key5,5,value5"})
    void testCsv2(String key, Integer value, String value2) {
        log.info("key:{},value:{},value2:{}", key, value, value2);
    }

    /**
     * 方法返回参数入参
     * 方法需要静态且无参
     * 如果是外部方法需要写权限定名：pers.guzx.common.util.类名#方法名
     * 支持Stream类型
     *
     * @param key
     * @param value
     */
    @ParameterizedTest
    @MethodSource(value = "provider")
    void testMethod(String key, Integer value) {
        log.info("key:{},value:{}", key, value);
    }

    static List<Arguments> provider() {
        return List.of(Arguments.of("key1", 1), Arguments.of("key2", 2));
    }


    /**
     * names支持正则表达式
     *
     * @param code
     */
    @ParameterizedTest
    @EnumSource(value = Code.class, names = {"MSG_EMAIL_FORMAT_ERROR", "FRIEND_IS_ADDED"}, mode = EnumSource.Mode.INCLUDE)
    void testMethod2(Code code) {
        log.info("code:{}", code.getMsg());
    }

}
