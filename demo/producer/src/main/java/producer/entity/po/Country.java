//package demo.entity.po;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import demo.entity.vo.CountryVO;
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.beans.BeanUtils;
//
//import java.io.Serializable;
//
///**
// * @author Guzx
// * @version 1.0
// * @date 2021/5/15 17:47
// * @describe
// */
//@TableName("country")
//@Builder
//@Data
//public class Country implements Serializable {
//
//
//
//    @TableId(value = "id",type = IdType.ASSIGN_ID)
//    private Long id;
//    private String name;
//    private String englishName;
//    private String island;
//    private String language;
//    private Long population;
//    private String grownDate;
//    @TableField(value = "code")
//    private Integer code;
//    private static final long serialVersionUID = 1L;
//
//    public CountryVO toCountryVO() {
//        CountryVO countryVO = new CountryVO();
//        BeanUtils.copyProperties(this,countryVO);
//        return countryVO;
//    }
//}
