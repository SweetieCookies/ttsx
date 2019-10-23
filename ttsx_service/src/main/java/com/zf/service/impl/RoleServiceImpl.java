package com.zf.service.impl;
import com.zf.dao.RoleDao;
import com.zf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public Set<String> queryAllRolesByUsername(String username) {
        return roleDao.queryAllRolesByUsername(username);
    }
}
