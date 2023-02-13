package pers.guzx.entity.demo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pers.guzx.entity.demo.po.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.BeanUtils;
import pers.guzx.validation.group.Insert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "国家基本信息")
public class CountryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分页参数，当前页码")
    @Min(0)
    private Integer current;
    @ApiModelProperty("分页参数，页大小")
    @Min(1)
    private Integer size;

    @ApiModelProperty("国家代码")
    @Range(min = 10000, max = 99999, message = "out of maximum range")
    @NotNull(message = "can not be empty")
    private Integer code;

    @ApiModelProperty("中文名称")
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String name;

    @ApiModelProperty("英文名称")
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String englishName;

    @ApiModelProperty("所属洲")
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String island;

    @ApiModelProperty("主要语言")
    @NotBlank(message = "can not be empty", groups = Insert.class)
    private String language;

    @ApiModelProperty("人口")
    @Min(value = 1)
    private Long population;

    @ApiModelProperty("建立日期")
    private String grownDate;

    public Country toCountry() {
        Country country = Country.builder().build();
        BeanUtils.copyProperties(this, country);
        return country;
    }
}
