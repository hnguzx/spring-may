package pers.guzx.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pers.guzx.demo.entity.po.Country;
import pers.guzx.demo.mapper.CountryMapper;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@ActiveProfiles("dev")
@SpringBootTest
public class CountryTest {

    @Resource
    private CountryMapper countryMapper;

    @Test
    public void testSelect(){
        List<Country> countries = countryMapper.selectList(null);
        assertEquals(1,countries.size());
    }
}
