package pers.guzx.demo.entity.vo;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import pers.guzx.demo.entity.po.Country;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
public class CountryVO {
    @Max(value = 99999,message = "out of maximum range")
    private Integer code;
    @NotBlank(message = "can not be empty")
    private String name;
    @NotBlank(message = "can not be empty")
    private String englishName;
    @NotBlank(message = "can not be empty")
    private String island;
    @NotBlank(message = "can not be empty")
    private String language;
    @Min(value = 1)
    private Integer population;
    @Past
    private Date grownTime;

    public Country toCountry() {
        Country country = new Country();
        BeanUtils.copyProperties(this,country);
        return country;
    }
}
