package pers.guzx.demo.controller;

import org.springframework.web.bind.annotation.*;
import pers.guzx.common.annotation.SysLog;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.enums.Code;
import pers.guzx.common.exception.BaseException;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@RestController
@RequestMapping("/jdbc")
public class JDBC {

    @Resource
    private CountryService countryService;

    @SysLog
    @GetMapping("/getSingle/{countryId}")
    public CountryVO getCountry(@PathVariable Integer countryId) {
        CountryVO country = countryService.getCountry(countryId);
        return country;
    }

    @GetMapping("/getMultiple")
    public List<CountryVO> getCountryList(@RequestParam Map<String,String> para){
        List<CountryVO> countries = countryService.getCountries();
        return countries;
    }

    @PostMapping("/addCountry")
    public Result<Integer> addCountry(@RequestBody @Valid CountryVO countryVO){
        int i = countryService.addCountry(countryVO);
        return Result.succeed(i);
    }
}
