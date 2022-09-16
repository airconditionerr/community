package com.airconditioner.community.service;

import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.NotificationDTO;
import com.airconditioner.community.dto.PaginationDTO;

import java.math.BigInteger;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:40
 **/
public interface NotificationService {
    /**
     * 根据 userId 获取 通知集合
     * @param id    userId
     * @param page  分页页码
     * @param size  分页页面大小
     * @return
     */
    PaginationDTO listByUserId(BigInteger id, Integer page, Integer size);

    /**
     * 根据 userId 获取 未读通知数
     * @param id
     * @return
     */
    Integer countUnreadByUserId(BigInteger id);

    /**
     * 根据 id 更新已读
     * @param id    通知 id
     * @param user
     * @return
     */
    NotificationDTO read(BigInteger id, User user);
}
