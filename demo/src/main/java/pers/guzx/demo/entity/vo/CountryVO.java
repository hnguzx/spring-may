package pers.guzx.demo.entity.vo;

import lombok.Data;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
public class CountryVO {
    private Integer code;
    private String name;
    private String englishName;
    private String island;
    private String language;
    private Integer population;
    private String grownTime;
}
