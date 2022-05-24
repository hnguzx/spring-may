package pers.guzx.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pers.guzx.common.entity.dto.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@ActiveProfiles("dev")
@AutoConfigureMybatis
@ExtendWith(SpringExtension.class)
@WebMvcTest(Demo.class)
public class DemoTest {

    @Autowired
    private Demo demo;

    @Test
    void testTestConn() {
        // Setup
        // Run the test
        final String result = demo.testConn();

        // Verify the results
        assertEquals("success", result);
    }

    @Test
    void testTestCommonResp1() {
        // Setup
        final Result<String> expectedResult = new Result<String>(null, 200, null);

        // Run the test
        final Result<String> result = demo.testCommonResp1();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testTestCommonResp2() {
        // Setup
        final Result<String> expectedResult = new Result<>(null, 200, "success message");

        // Run the test
        final Result<String> result = demo.testCommonResp2();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
