package com.airconditioner.community.service;

import com.airconditioner.community.bean.Comment;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.CommentDTO;
import com.airconditioner.community.enums.CommentTypeEnum;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:52
 **/
public interface CommentService {
    void insert(Comment comment, User user);

    List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type);
}
