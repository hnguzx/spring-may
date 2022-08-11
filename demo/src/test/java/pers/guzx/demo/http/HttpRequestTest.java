package pers.guzx.demo.http;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.util.JsonUtils;
import pers.guzx.demo.entity.vo.CountryVO;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    /**
     * 使用Apollo配置中心无法随机生成端口
     */
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 调用本服务
     */
    @Test
    void testLocalSimple() {
        String url = "http://127.0.0.1:" + port + "/conn";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        log.info("status:{},response: {}", forEntity.getStatusCode(), forEntity.getBody());
    }

    /**
     * 调用本地服务
     */
    @Test
    void testLocalGet() {
        String countryId = "1554010471998357505";
        String url = "http://127.0.0.1:" + port + "/jdbc/getSingle/" + countryId;
        ResponseEntity<Result> forEntity = restTemplate.getForEntity(url, Result.class);
        log.info("status:{},response: {}", forEntity.getStatusCode(), forEntity.getBody());
    }

    @Test
    void testLocalGetList() {
        Long current = 1L;
        Long size = 2L;
        String url = "http://127.0.0.1:" + port + "/jdbc/getMultiple/" + current + "/" + size;

        CountryVO countryVO = new CountryVO();
        countryVO.setName("new name");
        countryVO.setEnglishName("new english name");
        countryVO.setLanguage("new language");

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求
        HttpEntity<CountryVO> entity = new HttpEntity<>(countryVO, headers);

        ResponseEntity<Result> forEntity = restTemplate.postForEntity(url, entity, Result.class);
        log.info("status:{},response: {}", forEntity.getStatusCode(), forEntity.getBody());
    }

    @Test
    void testLocalPost() {
        String url = "http://127.0.0.1:" + port + "/jdbc/updateCountry";

        // 构建请求参数
        /*MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
        data.put("code", Collections.singletonList(20001));
        data.put("name", Collections.singletonList("new name"));
        data.put("englishName", Collections.singletonList("new english name"));
        data.put("island", Collections.singletonList("new island"));
        data.put("language", Collections.singletonList("new language"));*/

        /*CountryVO countryVO = new CountryVO();
        countryVO.setCode(20001);
        countryVO.setName("new name");
        countryVO.setEnglishName("new english name");
        countryVO.setLanguage("new language");*/

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 20001);
        jsonObject.addProperty("name", "new name2");
        jsonObject.addProperty("englishName", "new english name2");
        jsonObject.addProperty("island", "new island2");
        jsonObject.addProperty("language", "new language2");
        String jsonString = JsonUtils.toJsonString(jsonObject);
        log.info("jsonString: {}", jsonString);
        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        ResponseEntity<Result> result = restTemplate.postForEntity(url, entity, Result.class);
        log.info("status:{},response: {}", result.getStatusCode(), result.getBody());

        Result result1 = restTemplate.postForObject(url, entity, Result.class);
        log.info("status:{},response: {}", result1.getCode(), result1.getData());
    }

    @Test
    void testLocalPut() {
        String url = "http://127.0.0.1:" + port + "/jdbc/addCountry";
        CountryVO countryVO = new CountryVO();
        countryVO.setCode(20002);
        countryVO.setName("new name");
        countryVO.setEnglishName("new english name");
        countryVO.setIsland("island");
        countryVO.setLanguage("new language");

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求
        HttpEntity<CountryVO> entity = new HttpEntity<>(countryVO, headers);

        restTemplate.put(url, entity);
    }

    @Test
    void testLocalDelete() {
        String url = "http://127.0.0.1:" + port + "/jdbc/deleteCountry";
        CountryVO countryVO = new CountryVO();
        countryVO.setCode(20002);
        countryVO.setName("new name");
        countryVO.setEnglishName("new english name");
        countryVO.setIsland("island");
        countryVO.setLanguage("new language");

        String data = JsonUtils.toJsonString(countryVO);

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求
        HttpEntity<CountryVO> entity = new HttpEntity<>(countryVO, headers);

        ResponseEntity<Result> response = restTemplate.postForEntity(url, entity, Result.class);
        log.info("status:{},response: {}", response.getStatusCode(), response.getBody());
    }


    @Test
    void testRemote() {
        String url = "https://sit.airstarbank.com/app/v1/index/page/admin/list/notice";

        JsonObject json = new JsonObject();
        json.addProperty("pageNo", 1);
        json.addProperty("pageSize", 10);
        json.addProperty("status", "");
        String data = JsonUtils.toJsonString(json);

        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求
        HttpEntity<String> entity = new HttpEntity<>(data, headers);

        Object response = restTemplate.postForObject(url, entity, Object.class);

        log.info("response: {}", response.toString());
    }
}
