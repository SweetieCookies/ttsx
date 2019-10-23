package com.zf.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface RoleDao {
    Set<String>  queryAllRolesByUsername(@Param("username") String username);
}
