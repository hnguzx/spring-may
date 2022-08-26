package pers.guzx.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author 25446
 */
@TableName("sys_role")
@Data
@Builder
public class Role {
    private Integer roleId;
    private String roleName;
    private Boolean enabled;
    @TableField(exist = false)
    private List<Authority> authorities;
}
