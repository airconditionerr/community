package com.airconditioner.community.service.impl;

import com.airconditioner.community.entity.User;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/9 13:40
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过 GitHub 登录
     * @param user (accountId, name, avatarUrl, token)
     */
    @Override
    public void loginByGithub(User user) {
        User dbUser = userMapper.getByAccountId(user.getAccountId());
        if (dbUser == null) {
            // 插入
            user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            log.info("新建用户：accountId: {}, name: {}, token: {}, avatarUrl: {}", user.getName(), user.getAccountId(), user.getToken(), user.getAvatarUrl());
        } else {
            // 更新
            log.info("更新用户前：accountId: {}, name: {}, token: {}, avatarUrl: {}", user.getName(), user.getAccountId(), user.getToken(), user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(new Timestamp(System.currentTimeMillis()));
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
            log.info("更新用户后：accountId: {}, name: {}, token: {}, avatarUrl: {}", user.getName(), user.getAccountId(), user.getToken(), user.getAvatarUrl());
        }
    }
}
