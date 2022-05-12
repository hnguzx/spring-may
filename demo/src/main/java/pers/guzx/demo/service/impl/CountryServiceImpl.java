package pers.guzx.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.guzx.demo.entity.Country;
import pers.guzx.demo.mapper.CountryMapper;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Transactional
@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    @Override
    public Country getCountry(Integer id) {
        Country country = countryMapper.selectById(id);
        return country;
    }

    @Override
    public List<Country> getCountries() {
        List<Country> countries = countryMapper.selectList(null);
        return countries;
    }
}
