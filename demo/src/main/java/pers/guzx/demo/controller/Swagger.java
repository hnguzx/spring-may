package pers.guzx.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.model.Result;
import pers.guzx.demo.entity.Country;
import pers.guzx.demo.service.CountryService;

import javax.annotation.Resource;

@Api(tags = "测试swagger的Controller")
@RestController
public class Swagger {
    @Resource
    private CountryService countryService;

    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/swagger")
    public Result<String> swagger(@ApiParam(value = "参数的说明", required = true) String name) {
        return Result.succeed(name);
    }

    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/getSingle/{countryId}")
    public Result<Country> getCountry(@ApiParam(value = "参数的说明", required = true) @PathVariable Integer countryId) {
        Country country = countryService.getCountry(countryId);
        return Result.succeed(country);
    }
}
