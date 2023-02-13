package pers.guzx.producer.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.api.demo.producer.SwaggerApi;
import pers.guzx.common.entity.Result;
import pers.guzx.entity.demo.vo.CountryVO;
import pers.guzx.producer.service.CountryService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author 25446
 */
@RestController
public class SwaggerController implements SwaggerApi {
    @Resource
    private CountryService countryService;

    public Result<String> swagger(@RequestParam("name") String name) {
        return Result.succeed(name);
    }

    public Result<CountryVO> getCountry(@PathVariable Long countryId) {
        Optional<CountryVO> country = countryService.getCountryById(countryId);
        return Result.succeed(country.get());
    }
}
