package com.zf.service;

import com.zf.pojo.Status;
import com.zf.pojo.User;
import com.zf.utils.R;

/**
 * @author CoolDragon
 */
public interface UserService {
    Status register(User user);
   User loginByName(String username);
   User loginByEmail(String email);
   User loginByUsernameOrEmail(String username,String email);
}
