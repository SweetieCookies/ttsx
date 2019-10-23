package com.zf.service.impl;

import com.zf.constant.Constant;
import com.zf.dao.UserDao;
import com.zf.pojo.Status;
import com.zf.pojo.User;
import com.zf.service.UserService;
import com.zf.utils.R;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.EnumMap;
import java.util.UUID;

/**
 * @author CoolDragon
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Status register(User user) {
        String salt = UUID.randomUUID().toString();
        String password = new Sha256Hash(user.getPassword(), salt, Constant.TIMES).toBase64();
        user.setPassword(password);
        user.setSalt(salt);
        user.setRegisterTime(new Date());
        userDao.add(user);
        return new Status("ok");
    }
    @Override
    public User loginByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public User loginByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User loginByUsernameOrEmail(String username, String email) {
        return userDao.findBy(username, email);
    }
}
