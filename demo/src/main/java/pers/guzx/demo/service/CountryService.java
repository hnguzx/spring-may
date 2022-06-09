package pers.guzx.demo.service;

import pers.guzx.demo.entity.vo.CountryVO;

import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
public interface CountryService {
    CountryVO getCountry(Integer id);
    List<CountryVO> getCountries();
    int addCountry(CountryVO countryVO);
}
