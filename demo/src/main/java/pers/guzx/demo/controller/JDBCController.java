package pers.guzx.demo.controller;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guzx.common.annotation.SysLog;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.entity.vo.PageResult;
import pers.guzx.demo.service.CountryService;

import javax.validation.groups.Default;
import java.util.Optional;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@RestController
@RequestMapping("/jdbc")
public class JDBCController {

    @Autowired
    private CountryService countryService;

    @SysLog
    @GetMapping("/getSingle/{countryId}")
    public Result<CountryVO> getCountry(@PathVariable Long countryId) {
        Optional<CountryVO> country = countryService.getCountryById(countryId);
        if (country.isPresent()) {
            return Result.succeed(country.get());
        }
        return Result.succeed();
    }

    @PostMapping("/getMultiple/{current}/{size}")
    public Result<PageResult<CountryVO>> getCountryList(@RequestBody CountryVO countryVO,@PathVariable Long current, @PathVariable Long size) {
        PageResult<CountryVO> countryByCountry = countryService.getCountryByCountry(countryVO, current, size);
        return Result.succeed(countryByCountry);
    }

    @PutMapping("/addCountry")
    public Result<CountryVO> addCountry(@RequestBody @Validated({Insert.class, Default.class}) CountryVO countryVO) {
        boolean result = countryService.addCountry(countryVO);
        if (result) {
            return Result.succeed(countryVO);
        }
        return Result.failed();
    }

    @PostMapping("/deleteCountry")
    public Result<Boolean> deleteCountry(@RequestBody CountryVO countryVO) {
        boolean result = countryService.deleteCountry(countryVO);
        return Result.succeed(result);
    }

    @PostMapping("/updateCountry")
    public Result<CountryVO> updateCountry(@RequestBody @Validated CountryVO countryVO) {
        boolean result = countryService.updateCountry(countryVO);
        return result ? Result.succeed() : Result.failed();
    }
}
