package pers.guzx.demo.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.entity.vo.PageResult;

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
    @Cacheable(cacheNames = "country", keyGenerator = "keyGenerator", unless = "#result == null")
    Optional<CountryVO> getCountryById(Long id);

    /**
     * 根据条件查询列表
     *
     * @param country
     * @param current
     * @param size
     * @return
     */
    @Cacheable(cacheNames = "country")
    PageResult<CountryVO> getCountryByCountry(CountryVO country, Long current, Long size);

}
