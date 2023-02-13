package pers.guzx.demo.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import pers.guzx.common.validator.InsertGroup;
import pers.guzx.common.validator.UpdateGroup;
import pers.guzx.demo.entity.po.Country;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryVO {
    @Max(value = 99999, message = "out of maximum range")
    private Integer code;
    @NotBlank(message = "can not be empty")
    private String name;
    @NotBlank(message = "can not be empty")
    private String englishName;
    @NotBlank(message = "can not be empty")
    private String island;
    @NotBlank(message = "can not be empty")
    private String language;
    @Min(value = 1, groups = {InsertGroup.class, UpdateGroup.class})
    private Integer population;
    @Past
    private LocalDate grownTime;

    public Country toCountry() {
        Country country = new Country();
        BeanUtils.copyProperties(this, country);
        return country;
    }
}
