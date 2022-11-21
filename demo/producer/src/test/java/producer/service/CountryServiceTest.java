package producer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import producer.mapper.CountryMapper;
import producer.service.impl.CountryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pers.guzx.entity.PageResult;
import pers.guzx.entity.demo.po.Country;
import pers.guzx.entity.demo.vo.CountryVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    @Mock
    private CountryMapper countryMapper;

    @InjectMocks
    private CountryServiceImpl countryService;

    private Country country;
    private CountryVO countryVO;
    private List<Country> countries;
    private Page<Country> countryPage;

    @BeforeEach
    void initData() {
        country = Country.builder()
                .id(1L)
                .code(10001)
                .name("Test Country")
                .englishName("Test English Country")
                .island("Test")
                .grownDate("Test")
                .language("Test")
                .population(100000000L)
                .build();
        countries = new ArrayList<>();
        countries.add(country);
        countryVO = country.toCountryVO();
        countryPage = new Page<>();
        countryPage.setRecords(countries);
        countryPage.setCurrent(1L);
        countryPage.setSize(1L);
        countryPage.setTotal(1L);
    }

    @DisplayName("新增country信息")
    @Test
    void addCountry() {
        when(countryMapper.insert(argThat(Objects::nonNull))).thenReturn(1);
        boolean result = countryService.addCountry(countryVO);
        assertTrue(result);
    }

    @DisplayName("删除country信息")
    @Test
    void deleteCountry() {
        when(countryMapper.delete(argThat(Objects::nonNull))).thenReturn(1);
        boolean result = countryService.deleteCountry(countryVO);
        assertTrue(result);
    }

    @DisplayName("更新country信息")
    @Test
    void updateCountry() {
        when(countryMapper.update(argThat(Objects::nonNull), argThat(Objects::nonNull))).thenReturn(1);
        boolean result = countryService.updateCountry(countryVO);
        assertTrue(result);
    }

    @DisplayName("根据id获取country")
    @Test
    void getCountryById() {
        when(countryMapper.selectById(anyLong())).thenReturn(country);
        Optional<CountryVO> countryById = countryService.getCountryById(1L);
        assertNotNull(countryById.get());
    }

    @DisplayName("获取country列表")
    @Test
    void getCountryByCountry() {
        when(countryMapper.selectPage(argThat(Objects::nonNull), argThat(Objects::nonNull))).thenReturn(countryPage);
        PageResult<CountryVO> countryByCountry = countryService.getCountryByCountry(countryVO, 1L, 1L);
        log.info("CountryByCountry: " + countryByCountry.getTotal());
        assertNotNull(countryByCountry);
    }
}