package pers.guzx.demo.entity;

import lombok.Data;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Data
public class Country {
    private Integer id;
    private Integer code;
    private String name;
    private String englishName;
    private String island;
    private String language;
    private Integer population;
    private String grownTime;
}
