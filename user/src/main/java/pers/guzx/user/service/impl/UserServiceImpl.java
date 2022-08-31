package pers.guzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.guzx.user.entity.Authority;
import pers.guzx.user.entity.Role;
import pers.guzx.user.entity.User;
import pers.guzx.user.mapper.AuthorityMapper;
import pers.guzx.user.mapper.RoleMapper;
import pers.guzx.user.mapper.UserMapper;
import pers.guzx.user.service.AuthorityService;
import pers.guzx.user.service.RoleService;
import pers.guzx.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 25446
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, username).last("limit 1");
        User user = userMapper.selectOne(query);
        List<Role> roles;
        List<Authority> authorities = new ArrayList<>();
        if (user != null) {
            roles = roleService.getRoleByUserId(user.getUserId());
            if (!CollectionUtils.isEmpty(roles)) {
                user.setRoles(roles);
                roles.parallelStream().forEach(role ->{
                    List<Authority> authorityByRoleId = authorityService.getAuthorityByRoleId(role.getRoleId());
                    if(!CollectionUtils.isEmpty(authorityByRoleId)){
                        authorities.addAll(authorityByRoleId);
                    }
                });

            }
            List<Authority> authorityByUserId = authorityService.getAuthorityByUserId(user.getUserId());
            if(!CollectionUtils.isEmpty(authorityByUserId)){
                authorities.addAll(authorityByUserId);
            }
            user.setAuthorities(authorities);
        }

        return user;
    }
}
