package com.airconditioner.community.mapper;

import com.airconditioner.community.bean.Notification;
import com.airconditioner.community.bean.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:09
 **/
@Mapper
public interface NotificationMapper {
    void insert(Notification notification);

    Integer countNotificationByUserId(Integer userId);

    Integer countUnreadNotificationByUserId(Integer userId, Integer status);

    List<Notification> selectNotificationByUserId(Integer userId, Integer offset, Integer size);

    Notification selectNotificationById(Integer id);

    void updateStatusById(Notification notification);
}
