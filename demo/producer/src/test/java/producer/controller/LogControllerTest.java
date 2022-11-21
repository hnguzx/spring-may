package producer.controller;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller层测试，只添加@WebMvcTest注解即可直接注入MockMvc直接进行http调用，可指定特定Controller
 * 如果系统接入了Mybatis则需要添加mybatis-spring-boot-starter-test依赖
 * 并在Test类中添加@AutoconfigureMybatis注解
 */
@AutoConfigureMybatis
@WebMvcTest(LogController.class)
class LogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLog() throws java.lang.Exception {

        final MockHttpServletResponse response = mockMvc.perform(get("/log")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse();

        // Verify the results
        assertEquals("", response.getContentAsString());
    }
}
