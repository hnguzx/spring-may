package pers.guzx.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.guzx.user.entity.Authority;
import pers.guzx.user.entity.Role;
import pers.guzx.user.entity.User;
import pers.guzx.user.entity.UserRole;
import pers.guzx.user.mapper.RoleMapper;
import pers.guzx.user.mapper.UserRoleMapper;
import pers.guzx.user.service.RoleService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 25446
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRoleByUserId(Integer userId) {
        List<Role> roles = new ArrayList<>();
        if (userId != null) {
            LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserRole::getUserId, userId);
            List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);

            if (!CollectionUtil.isEmpty(userRoles)) {
                userRoles.parallelStream().forEach(userRole -> {
                    Role role = roleMapper.selectById(userRole.getRoleId());
                    if (role != null) {
                        roles.add(role);
                    }
                });
            }
        }
        return roles;
    }
}
