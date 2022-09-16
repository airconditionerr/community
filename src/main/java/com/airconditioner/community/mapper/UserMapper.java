package com.airconditioner.community.mapper;

import com.airconditioner.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 16:13
 **/
@Mapper
public interface UserMapper {
    /**
     * 新建用户
     * @param user   (accountId, name, avatarUrl, token)
     */
    void insert(User user);

    User getByToken(String token);

    /**
     * 根据 id 获取 单个 用户
     * @param id
     * @return
     */
    User getById(BigInteger id);

    /**
     * 根据 accountId 获取 单个 用户
     * @param accountId
     * @return
     */
    User getByAccountId(String accountId);

    /**
     * 更新用户
     * @param dbUser    (accountId, name, avatarUrl, token)
     */
    void update(User dbUser);
}
