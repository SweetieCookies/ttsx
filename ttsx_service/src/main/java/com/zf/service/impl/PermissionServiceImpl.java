package com.zf.service.impl;


import com.zf.dao.PermissionDao;
import com.zf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public Set<String> queryAllPermissionByUsername(String username) {
        return permissionDao.queryAllPermissionByUsername(username);
    }
}
