package pers.guzx.user.service;

import pers.guzx.user.entity.Authority;
import pers.guzx.user.entity.User;

import java.util.List;

/**
 * @author 25446
 */
public interface AuthorityService {
    List<Authority> getAuthorityByUserId(Integer userId);

    List<Authority> getAuthorityByRoleId(Integer roleId);

    List<Authority> getAuthorityByUser(User user);
}
