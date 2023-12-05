package pers.guzx.entity.demo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import pers.guzx.entity.demo.vo.CountryVO;

import java.io.Serializable;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@TableName("country")
@Builder
@Data
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "code")
    private String code;
    private String name;
    private String englishName;
    private String island;
    private String language;
    private Long population;
    private String grownDate;

    public CountryVO toCountryVO() {
        CountryVO countryVO = new CountryVO();
        BeanUtils.copyProperties(this,countryVO);
        return countryVO;
    }
}
