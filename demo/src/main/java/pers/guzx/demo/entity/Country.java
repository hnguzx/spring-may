package pers.guzx.demo.entity;

import lombok.Data;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
//@ApiModel(value = "Country", description = "国家")
@Data
public class Country {
//    @ApiModelProperty(value = "主键", name = "id", hidden = true)
    private Integer id;
//    @ApiModelProperty(value = "国家编码", name = "code", example = "1000", required = true)
    private Integer code;
//    @ApiModelProperty(value = "国家中文名称", name = "name", example = "中华人民共和国", required = true)
    private String name;
//    @ApiModelProperty(value = "国家英文名称", name = "englishName", example = "People's Republic of China", required = false)
    private String englishName;
//    @ApiModelProperty(value = "国家所属洲", name = "island", example = "亚洲", required = true)
    private String island;
//    @ApiModelProperty(value = "国家官方语言", name = "language", example = "中文", required = true)
    private String language;
//    @ApiModelProperty(value = "国家人口", name = "population", example = "100000000", required = false)
    private Integer population;
//    @ApiModelProperty(value = "国家成立日期", name = "grownTime", example = "1949-10-01", required = false)
    private String grownTime;
}
