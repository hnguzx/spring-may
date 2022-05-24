package pers.guzx.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.demo.entity.vo.CountryVO;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;

//@Api(tags = "测试swagger的Controller")
@RestController
public class Swagger {
    @Resource
    private CountryService countryService;

//    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/swagger")
    public Result<String> swagger(String name) {
        return Result.succeed(name);
    }

//    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/getSingle/{countryId}")
    public Result<CountryVO> getCountry(@PathVariable Integer countryId) {
        CountryVO country = countryService.getCountry(countryId);
        return Result.succeed(country);
    }
}
