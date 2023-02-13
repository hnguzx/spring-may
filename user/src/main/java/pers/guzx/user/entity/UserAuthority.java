package pers.guzx.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 25446
 */
@TableName("user_authority")
@Data
public class UserAuthority implements Serializable {
    @TableId
    private Integer id;
    private Integer userId;
    private Integer authorityId;
}
