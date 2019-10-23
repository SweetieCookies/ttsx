package com.zf.service;

import java.util.Set;

public interface RoleService {
    Set<String> queryAllRolesByUsername(String username);

}
