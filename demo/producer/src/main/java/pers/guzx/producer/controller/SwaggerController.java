package pers.guzx.producer.controller;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;
import pers.guzx.producer.service.CountryService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author 25446
 */
//@Api(tags = "测试swagger的Controller")
@RestController
public class SwaggerController {
    @Resource
    private CountryService countryService;

//    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/swagger")
    public Result<String> swagger(@RequestParam("name") String name) {
        return Result.succeed(name);
    }

//    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/getSingle/{countryId}")
    public Result<CountryVO> getCountry(@PathVariable Long countryId) {
        Optional<CountryVO> country = countryService.getCountryById(countryId);
        return Result.succeed(country.get());
    }
}
