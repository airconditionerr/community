package com.airconditioner.community.service.impl;

import com.airconditioner.community.bean.User;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/9 13:40
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void loginByGithub(User user) {
        User dbUser = userMapper.findUserByAccountId(user.getAccountId());
        if (dbUser == null){
            // 插入
            user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            user.setGmtModified(user.getGmtCreate());
            userMapper.insertByGithub(user);
        } else {
            // 更新
            dbUser.setGmtModified(new Timestamp(System.currentTimeMillis()));
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateByGithub(dbUser);
        }
    }
}
