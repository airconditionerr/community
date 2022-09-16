package com.airconditioner.community.service;

import com.airconditioner.community.entity.User;

/**
 * @Author AirConditioner
 * @Date 2022/9/9 13:40
 **/
public interface UserService {
    /**
     * 通过 GitHub 登录
     * @param user  (accountId, name, avatarUrl, token)
     */
    void loginByGithub(User user);
}
