package pers.guzx.demo.service;

import pers.guzx.demo.entity.Country;

import java.util.List;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
public interface CountryService {
    Country getCountry(Integer id);
    List<Country> getCountries();
}
