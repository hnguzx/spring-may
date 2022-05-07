package pers.guzx.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pers.guzx.common.model.Result;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
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

    @Test
    void testTestCommonResp3() {
        // Setup
        final Result<String> expectedResult = new Result<>("Data", 0, "message");

        // Run the test
        final Result<String> result = demo.testCommonResp3();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
