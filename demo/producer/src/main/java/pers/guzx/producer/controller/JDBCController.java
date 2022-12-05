package pers.guzx.producer.controller;

import pers.guzx.producer.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.guzx.common.annotation.SysLog;
import pers.guzx.entity.PageResult;
import pers.guzx.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;

import javax.validation.groups.Default;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Slf4j
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
    public Result<PageResult<CountryVO>> getCountryList(@RequestBody CountryVO countryVO, @PathVariable Long current, @PathVariable Long size) {
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

    @GetMapping("/getCountryList")
    public Result<List<CountryVO>> getCountryList(@RequestParam(name = "code") String code,
                                                  @RequestParam(name = "name") String name,
                                                  @RequestParam(name = "englishName") String englishName,
                                                  @RequestParam(name = "type") String type) {
        List<CountryVO> countryByCodeAndNameAndEnglishName = new ArrayList<CountryVO>();
        if ("1".equals(type)) {
            countryByCodeAndNameAndEnglishName = countryService.getCountryByCodeOrNameOrEnglishName(code, name, englishName);
        } else {
            countryByCodeAndNameAndEnglishName = countryService.getCountryByCodeAndNameAndEnglishName(code, name, englishName);
        }
        return Result.succeed(countryByCodeAndNameAndEnglishName);
    }

    @GetMapping("/batchInsert")
    public Result<List<CountryVO>> batchInsert(@RequestParam(name = "code") String code,
                                               @RequestParam(name = "name") String name,
                                               @RequestParam(name = "englishName") String englishName,
                                               @RequestParam(name = "type") String type) throws IntrospectionException {
        long start = System.currentTimeMillis();
        countryService.selectAndSaveBatch(code, name, englishName);
        long time = System.currentTimeMillis() - start;
        log.info("Insert batch time: {}", time);
            return Result.succeed();
    }
}
