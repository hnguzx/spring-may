package pers.guzx.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.guzx.demo.entity.po.Country;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.mapper.CountryMapper;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    @Override
    public CountryVO getCountry(Integer id) {
        Country country = countryMapper.selectById(id);
        CountryVO countryVO = country.toCountryVO();
        return countryVO;
    }

    @Override
    public List<CountryVO> getCountries() {
        List<Country> countries = countryMapper.selectList(null);
        List<CountryVO> countryVOS = new ArrayList<>();
        for (Country country: countries){
            CountryVO countryVO = country.toCountryVO();
            countryVOS.add(countryVO);
        }
        return countryVOS;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addCountry(CountryVO countryVO) {
        Country country = countryVO.toCountry();
        return countryMapper.insert(country);
    }
}
