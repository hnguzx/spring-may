package pers.guzx.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pers.guzx.common.exception.DAOException;
import pers.guzx.common.exception.ServiceException;
import pers.guzx.demo.entity.po.Country;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.entity.vo.PageResult;
import pers.guzx.demo.mapper.CountryMapper;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Slf4j
@Service
public class CountryServiceImpl implements CountryService {

    @Resource
    private CountryMapper countryMapper;

    @Override
    public boolean addCountry(CountryVO countryVO) {
        try {
            Country country = countryVO.toCountry();
            int insert = countryMapper.insert(country);
            return insert > 0;
        } catch (Exception e) {
            throw new ServiceException("add country exception", e);
        }
    }

    @Override
    public boolean deleteCountry(CountryVO countryVO) {
        try {
            QueryWrapper<Country> queryWrapper = new QueryWrapper();

            queryWrapper.eq(countryVO.getCode() != null, "code", countryVO.getCode());
            queryWrapper.eq(countryVO.getName() != null, "name", countryVO.getName());
            queryWrapper.eq(countryVO.getEnglishName() != null, "english_name", countryVO.getEnglishName());
            int delete = countryMapper.delete(queryWrapper);
            if (delete == 0) {
                log.warn("delete country ：{} fail,country not exist", countryVO.getName());
                return false;
            } else if (delete == 1) {
                return true;
            } else {
                throw new ServiceException("delete country error,delete country more than one");
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("delete country exception", e);
        }
    }

    @Override
    public boolean updateCountry(CountryVO countryVO) {
        try {
            UpdateWrapper<Country> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("code", countryVO.getCode());
            int update = countryMapper.update(countryVO.toCountry(), updateWrapper);
            if (update == 0) {
                log.warn("update country ：{} fail", countryVO.getCode());
                return false;
            } else if (update == 1) {
                return true;
            } else {
                log.error("update country count:{}", update);
                throw new ServiceException("update country error,update country more than one");
            }
        } catch (Exception e) {
            throw new ServiceException("update country more than one", e);
        }
    }

    @Override
    public Optional<CountryVO> getCountryById(Long id) {
        try {
            Optional<Country> country = Optional.ofNullable(countryMapper.selectById(id));
            if (country.isPresent()) {
                CountryVO countryVO = country.get().toCountryVO();
                return Optional.of(countryVO);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new DAOException("get country exception", e);
        }
    }

    @Override
    public PageResult<CountryVO> getCountryByCountry(CountryVO countryVO, Long current, Long size) {

        //LambdaQueryWrapper<Country> query = new LambdaQueryWrapper<>();
        //query.eq(Country::getCode, countryVO.getCode()).orderByDesc(Country::getId).last("limit 1");

        QueryWrapper<Country> query = new QueryWrapper<>();
        query.eq(countryVO.getCode() != null, "code", countryVO.getCode());
        query.like(countryVO.getName() != null, "name", countryVO.getName());
        query.like(countryVO.getEnglishName() != null, "english_name", countryVO.getEnglishName());
        Page<Country> page = new Page<>(current, size);

        try {
            Page<Country> countryPage = countryMapper.selectPage(page, query);
            List<Country> records = countryPage.getRecords();
            List<CountryVO> collect = new ArrayList<>();
            if (!CollectionUtils.isEmpty(records)) {
                collect = records.parallelStream()
                        .map(Country::toCountryVO)
                        .collect(Collectors.toList());

            }
            return PageResult.build(countryPage, collect);
        } catch (Exception e) {
            throw new DAOException("get country list exception", e);
        }
    }
}
