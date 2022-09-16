package com.airconditioner.community.service;

import com.airconditioner.community.entity.Comment;
import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.CommentDTO;
import com.airconditioner.community.enums.CommentTypeEnum;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:52
 **/
public interface CommentService {
    /**
     * 创建评论
     *
     * @param comment (parentId, content, type, gmtModified, gmtCreate, commentator)
     * @param user    ()
     */
    void insert(Comment comment, User user);

    List<CommentDTO> listByTargetId(BigInteger id, CommentTypeEnum type);
}
