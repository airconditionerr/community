package com.airconditioner.community.mapper;

import com.airconditioner.community.entity.Notification;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:09
 **/
@Mapper
public interface NotificationMapper {
    void insert(Notification notification);

    Integer countByUserId(BigInteger userId);

    Integer countUnreadByUserId(BigInteger userId, Integer isRead);

    List<Notification> getByUserId(BigInteger userId, Integer offset, Integer size);

    Notification getById(BigInteger id);

    void updateIsReadById(Notification notification);
}
