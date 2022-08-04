package pers.guzx.demo.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import pers.guzx.demo.entity.po.Country;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
public class CountryVO {
    @Range(min = 10000, max = 99999, message = "out of maximum range", groups = Update.class)
    @NotNull(message = "can not be empty", groups = {Insert.class, Update.class})
    private Integer code;
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String name;
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String englishName;
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String island;
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String language;
    @Min(value = 1)
    private Long population;
    private String grownDate;

    public Country toCountry() {
        Country country = Country.builder().build();
        BeanUtils.copyProperties(this, country);
        return country;
    }
}
