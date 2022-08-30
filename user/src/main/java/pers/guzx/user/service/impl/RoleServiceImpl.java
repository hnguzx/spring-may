package pers.guzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.guzx.user.entity.Role;
import pers.guzx.user.entity.User;
import pers.guzx.user.mapper.RoleMapper;
import pers.guzx.user.service.RoleService;

/**
 * @author 25446
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role getRoleById(Integer roleId) {
        Role role = roleMapper.selectById(roleId);
        return role;
    }
}
