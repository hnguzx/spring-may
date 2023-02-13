package pers.guzx.producer.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import pers.guzx.common.entity.PageResult;
import pers.guzx.entity.demo.vo.CountryVO;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
public interface CountryService {
    /**
     * 新增
     *
     * @param countryVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean addCountry(CountryVO countryVO);

    /**
     * 删除
     *
     * @param countryVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean deleteCountry(CountryVO countryVO);

    /**
     * 修改
     *
     * @param countryVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateCountry(CountryVO countryVO);

    /**
     * 根据主键查询单笔
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "country1", unless = "#result == null")
    Optional<CountryVO> getCountryById(Long id);

    /**
     * 根据条件查询列表
     *
     * @param country
     * @param current
     * @param size
     * @return
     */
    @Cacheable(cacheNames = "country2", key = "#root.caches[0].name")
    PageResult<CountryVO> getCountryByCountry(CountryVO country, Long current, Long size);

    public List<CountryVO> getCountryByCodeOrNameOrEnglishName(String code, String name, String englishName);

    public List<CountryVO> getCountryByCodeAndNameAndEnglishName(String code,String name,String englishName);

    public void selectAndSaveBatch(String code, String name, String englishName) throws IntrospectionException;

}
