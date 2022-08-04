package pers.guzx.demo.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pers.guzx.demo.entity.po.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
class CountryMapperTest {
    @Autowired
    private CountryMapper mapper;

    private Country country;

    @BeforeEach
    void setUp() {
        country = Country.builder()
                .id(3L)
                .code(20002)
                .name("name3")
                .englishName("englishName3")
                .island("english")
                .population(1000L)
                .grownDate("20090101")
                .language("english")
                .build();
    }

    @Test
    @Transactional
    void testInserter() {
        int count = mapper.insert(country);
        assertEquals(1, count);
    }

    @Test
    void testSelectCountryById() {
        Country result = mapper.selectById(1L);
        assertNotNull(result);
        log.info("Country: " + result);
    }
}