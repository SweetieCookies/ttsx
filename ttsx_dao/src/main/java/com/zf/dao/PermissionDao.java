package com.zf.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface PermissionDao {
    Set<String> queryAllPermissionByUsername(@Param("username") String username);
}
