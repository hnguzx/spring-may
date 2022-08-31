package pers.guzx.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.guzx.user.entity.Authority;
import pers.guzx.user.entity.RoleAuthority;
import pers.guzx.user.entity.User;
import pers.guzx.user.entity.UserAuthority;
import pers.guzx.user.mapper.AuthorityMapper;
import pers.guzx.user.mapper.RoleAuthorityMapper;
import pers.guzx.user.mapper.UserAuthorityMapper;
import pers.guzx.user.service.AuthorityService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 25446
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private UserAuthorityMapper userAuthorityMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;


    @Override
    public List<Authority> getAuthorityByUserId(Integer userId) {
        List<Authority> authorities = new ArrayList<>();
        if (userId != null) {
            LambdaQueryWrapper<UserAuthority> query = new LambdaQueryWrapper<>();
            query.eq(UserAuthority::getUserId, userId);
            List<UserAuthority> userAuthorities = userAuthorityMapper.selectList(query);
            if (!CollectionUtils.isEmpty(userAuthorities)) {
                userAuthorities.parallelStream().forEach(userAuthority -> {
                    Authority authority = authorityMapper.selectById(userAuthority.getAuthorityId());
                    if (authority != null) {
                        authorities.add(authority);
                    }
                });
            }
        }
        return authorities;
    }

    @Override
    public List<Authority> getAuthorityByRoleId(Integer roleId) {
        List<Authority> authorities = new ArrayList<>();
        if (roleId != null) {
            LambdaQueryWrapper<RoleAuthority> query = new LambdaQueryWrapper<>();
            query.eq(RoleAuthority::getRoleId, roleId);
            List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectList(query);
            if (!CollectionUtils.isEmpty(roleAuthorities)) {
                roleAuthorities.parallelStream().forEach(roleAuthority -> {
                    Authority authority = authorityMapper.selectById(roleAuthority.getAuthorityId());
                    if (authority != null) {
                        authorities.add(authority);
                    }
                });
            }
        }
        return authorities;
    }

    @Override
    public List<Authority> getAuthorityByUser(User user) {
        return null;
    }
}
