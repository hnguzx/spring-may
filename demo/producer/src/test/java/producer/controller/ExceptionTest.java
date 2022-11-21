package producer.controller;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMybatis
@WebMvcTest(ExceptionController.class)
class ExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCommonException() throws java.lang.Exception {
        final MockHttpServletResponse response = mockMvc.perform(get("/exception/common")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

    }

    @Test
    void testBaseException() throws java.lang.Exception {

        final MockHttpServletResponse response = mockMvc.perform(get("/exception/base")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();
        response.getContentAsString();

    }
}
