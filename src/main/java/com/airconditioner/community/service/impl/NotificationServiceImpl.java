package com.airconditioner.community.service.impl;

import com.airconditioner.community.entity.Notification;
import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.NotificationDTO;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.enums.NotificationIsReadEnum;
import com.airconditioner.community.enums.NotificationTypeEnum;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.exception.CustomizeException;
import com.airconditioner.community.mapper.NotificationMapper;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:40
 **/
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    public PaginationDTO listByUserId(BigInteger userId, Integer page, Integer size) {
        // 分页DTO 集合
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();


        Integer totalPage;
        Integer totalCount = notificationMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }


        paginationDTO.setPagination(totalPage, page);


        Integer offset = size * (page - 1);
        // 问题 集合
        List<Notification> notificationList = notificationMapper.getByUserId(userId, offset, size);

        if (notificationList.size() == 0){
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOS);
        return paginationDTO;
    }

    @Override
    public Integer countUnreadByUserId(BigInteger id) {
        return notificationMapper.countUnreadByUserId(id, NotificationIsReadEnum.UNREAD.getIsRead());
    }

    @Override
    public NotificationDTO read(BigInteger id, User user) {
        Notification notification = notificationMapper.getById(id);
        if (notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setIsRead(NotificationIsReadEnum.READ.getIsRead());
        notificationMapper.updateIsReadById(notification);


        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
