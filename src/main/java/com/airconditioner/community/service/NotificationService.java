package com.airconditioner.community.service;

import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.NotificationDTO;
import com.airconditioner.community.dto.PaginationDTO;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:40
 **/
public interface NotificationService {
    PaginationDTO list(Integer id, Integer page, Integer size);

    Integer unreadCount(Integer id);

    NotificationDTO read(Integer id, User user);
}
