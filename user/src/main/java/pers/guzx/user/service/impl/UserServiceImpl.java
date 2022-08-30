package pers.guzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pers.guzx.user.entity.Role;
import pers.guzx.user.entity.User;
import pers.guzx.user.mapper.AuthorityMapper;
import pers.guzx.user.mapper.RoleMapper;
import pers.guzx.user.mapper.UserMapper;
import pers.guzx.user.service.AuthorityService;
import pers.guzx.user.service.RoleService;
import pers.guzx.user.service.UserService;

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
    private AuthorityService authorityService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUsername, username).last("limit 1");
        User user = userMapper.selectOne(query);
        if (user != null) {
            Role roleById = roleService.getRoleById(user.getRoleId());
        }


        return user;
    }
}
