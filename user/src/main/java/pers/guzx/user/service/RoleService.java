package pers.guzx.user.service;

import pers.guzx.user.entity.Role;

import java.util.List;

/**
 * @author 25446
 */
public interface RoleService {

    List<Role> getRoleByUserId(Integer userId);

}
