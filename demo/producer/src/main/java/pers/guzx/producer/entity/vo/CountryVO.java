//package demo.entity.vo;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.apache.ibatis.annotations.Insert;
//import org.hibernate.validator.constraints.Range;
//import org.springframework.beans.BeanUtils;
//import pers.guzx.entity.demo.po.Country;
//
//import javax.validation.constraints.*;
//import java.io.Serializable;
//
///**
// * @author Guzx
// * @version 1.0
// * @date 2021/5/15 17:47
// * @describe
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CountryVO implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Range(min = 10000, max = 99999, message = "out of maximum range")
//    @NotNull(message = "can not be empty")
//    private Integer code;
//    @NotBlank(message = "can not be empty", groups = Insert.class)
//    private String name;
//    @NotBlank(message = "can not be empty", groups = Insert.class)
//    private String englishName;
//    @NotBlank(message = "can not be empty", groups = Insert.class)
//    private String island;
//    @NotBlank(message = "can not be empty", groups = Insert.class)
//    private String language;
//    @Min(value = 1)
//    private Long population;
//    private String grownDate;
//
//    public Country toCountry() {
//        Country country = Country.builder().build();
//        BeanUtils.copyProperties(this, country);
//        return country;
//    }
//}
