package pers.guzx.demo.controller;

import org.springframework.web.bind.annotation.*;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;
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
}
