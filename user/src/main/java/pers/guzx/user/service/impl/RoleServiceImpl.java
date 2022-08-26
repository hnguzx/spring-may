package pers.guzx.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.guzx.user.mapper.RoleMapper;
import pers.guzx.user.service.RoleService;

/**
 * @author 25446
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
}
