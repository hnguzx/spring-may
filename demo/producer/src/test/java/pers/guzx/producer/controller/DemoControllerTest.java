package pers.guzx.producer.controller;

import pers.guzx.producer.config.ConfigProperties;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 读取配置文件中信息需要添加spring-boot-configuration-processor依赖
 * 测试类上添加@ImportAutoConfiguration(RefreshAutoConfiguration.class)
 * 获取指定配置文件中的信息，可通过在测试类上添加@ActiveProfiles注解指定环境
 * get方法的不同传参
 */
@ActiveProfiles("dev")
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@AutoConfigureMybatis
@WebMvcTest(DemoController.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigProperties mockConfigProperties;

    @Test
    void testTestConn() throws java.lang.Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/conn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testSuccessResp1() throws java.lang.Exception {
        // Setup
        when(mockConfigProperties.getTimeout()).thenReturn("result");

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/successResp1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testSuccessResp2() throws java.lang.Exception {
        // Setup
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("name", Collections.singletonList("pers/guzx"));
        params.put("age", Collections.singletonList("25"));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/successResp2")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .params(params)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testErrorResp1() throws java.lang.Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/errorResp1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testErrorResp2() throws java.lang.Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/errorResp2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();
    }
}
