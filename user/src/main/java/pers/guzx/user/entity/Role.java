package pers.guzx.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author 25446
 * 后续可扩展为分部门，不同部门权限不同，上级拥有下级全部权限
 */
@TableName("role")
@Data
public class Role {
    @TableId
    private Integer roleId;
    private String roleName;
    private Boolean enabled;
    @TableField(exist = false)
    private List<Authority> authorities;
}
