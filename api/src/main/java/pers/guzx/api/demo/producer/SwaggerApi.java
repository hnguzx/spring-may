package pers.guzx.api.demo.producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pers.guzx.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;

@Api(tags = "测试swagger的Controller")
@FeignClient("producer-server")
public interface SwaggerApi {

    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/swagger")
    public Result<String> swagger(@RequestParam("name") String name);

    @ApiOperation(value = "测试swagger的方法注解", notes = "方法的补充说明，比如参数的类型与是否必输")
    @GetMapping("/getSingle/{countryId}")
    public Result<CountryVO> getCountry(@PathVariable Long countryId);

}
