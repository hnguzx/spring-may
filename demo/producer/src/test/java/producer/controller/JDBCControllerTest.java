package producer.controller;

import producer.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import pers.guzx.common.util.JsonUtils;
import pers.guzx.entity.PageResult;
import pers.guzx.entity.demo.vo.CountryVO;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller层测试，只添加@WebMvcTest注解即可直接注入MockMvc直接进行http调用，可指定特定Controller
 * 如果系统接入了Mybatis则需要添加mybatis-spring-boot-starter-test依赖
 * 并在Test类中添加@AutoconfigureMybatis注解
 * @MockBean将mock对象添加到Spring上下文中
 * get 可以在url中添加参数也可以在body中添加参数
 */
@Slf4j
@AutoConfigureMybatis
@WebMvcTest(JDBCController.class)
class JDBCControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService mockCountryService;

    /**
     * get请求，url中添加参数
     *
     * @throws java.lang.Exception
     */
    @Test
    void testGetCountry() throws java.lang.Exception {
        // Setup
        // Configure CountryService.getCountryById(...).
        final CountryVO countryVO1 = new CountryVO();
        countryVO1.setCode(0);
        countryVO1.setName("name");
        countryVO1.setEnglishName("englishName");
        countryVO1.setIsland("island");
        countryVO1.setLanguage("language");
        countryVO1.setPopulation(0L);
        countryVO1.setGrownDate("grownDate");
        final Optional<CountryVO> countryVO = Optional.of(countryVO1);
        when(mockCountryService.getCountryById(0L)).thenReturn(countryVO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/jdbc/getSingle/{countryId}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetCountry_CountryServiceReturnsAbsent() throws java.lang.Exception {
        // Setup
        when(mockCountryService.getCountryById(0L)).thenReturn(Optional.empty());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/jdbc/getSingle/{countryId}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * get请求，url添加参数，且接受pojo类参数
     *
     * @throws java.lang.Exception
     */
    @Test
    void testGetCountryList() throws java.lang.Exception {
        // Setup
        // Configure CountryService.getCountryByCountry(...).
        final CountryVO countryVO = new CountryVO();
        countryVO.setCode(0);
        countryVO.setName("name");
        countryVO.setEnglishName("englishName");
        countryVO.setIsland("island");
        countryVO.setLanguage("language");
        countryVO.setPopulation(0L);
        countryVO.setGrownDate("grownDate");
        final PageResult<CountryVO> countryVOPageResult = new PageResult<>(1L, 1L, 1L, List.of(countryVO));
        when(mockCountryService.getCountryByCountry(new CountryVO(), 1L, 1L)).thenReturn(countryVOPageResult);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/jdbc/getMultiple/{current}/{size}", 1, 1)
                        .content("{\"name\":\"国\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * put请求，新增数据
     *
     * @throws java.lang.Exception
     */
    @Test
    void testAddCountry() throws java.lang.Exception {
        // Setup
        when(mockCountryService.addCountry(argThat(Objects::nonNull))).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/jdbc/addCountry")
                        .content("{\"code\":10005,\n" +
                                "\"name\":\"澳大利亚联邦\",\n" +
                                "\"englishName\":\"Commonwealth of Australia\",\n" +
                                "\"island\":\"大洋洲\",\n" +
                                "\"language\":\"英语\",\n" +
                                "\"population\":25690000,\n" +
                                "\"grownDate\":\"17880126\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        String contentAsString = response.getContentAsString(StandardCharsets.UTF_8);

        String data = JsonUtils.getAsString(contentAsString, "data");
        int code = JsonUtils.getAsInt(contentAsString, "code");
        log.info("返回code:{},data:{}", code, data);

        /*Iterator<Map.Entry<String, JsonElement>> iterator = JsonUtil.getIteratorFromJsonString(contentAsString);
        while (iterator.hasNext()) {
            Map.Entry<String, JsonElement> next = iterator.next();
            String key = next.getKey();
            JsonElement value = next.getValue();
            //log.info("key:{},value:{}", key, JsonUtil.toJsonString(value));
            if ("data".equalsIgnoreCase(key)) {
                Iterator<Map.Entry<String, JsonElement>> iteratorFromJsonElement = JsonUtil.getIteratorFromJsonElement(value);
                while (iteratorFromJsonElement.hasNext()) {
                    Map.Entry<String, JsonElement> nextFromJsonElement = iteratorFromJsonElement.next();
                    String keyFromJsonElement = nextFromJsonElement.getKey();
                    JsonElement valueFromJsonElement = nextFromJsonElement.getValue();
                    log.info("key:{},value:{}", keyFromJsonElement, JsonUtil.toJsonString(valueFromJsonElement));
                }
            }

        }*/

    }

    /**
     * delete请求，删除数据
     *
     * @throws java.lang.Exception
     */
    @Test
    void testDeleteCountry() throws java.lang.Exception {
        // Setup
        when(mockCountryService.deleteCountry(argThat(Objects::nonNull))).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/jdbc/deleteCountry")
                        .content("{\"code\":10001}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    /**
     * post请求，更新数据
     *
     * @throws java.lang.Exception
     */
    @Test
    void testUpdateCountry() throws java.lang.Exception {
        // Setup
        when(mockCountryService.updateCountry(argThat(Objects::nonNull))).thenReturn(true);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/jdbc/updateCountry")
                        .content("{\"code\":10001}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
