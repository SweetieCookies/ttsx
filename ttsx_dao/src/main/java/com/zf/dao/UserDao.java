package com.zf.dao;

import com.zf.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author CoolDragon
 */
public interface UserDao {
    void add(User user);
    User findBy(@Param("username") String username,@Param("email") String email);
    User findByName(@Param("username")String username);
    User findByEmail(@Param("email")String email);
}
