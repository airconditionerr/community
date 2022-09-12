package com.airconditioner.community.service;

import com.airconditioner.community.bean.Comment;
import com.airconditioner.community.dto.CommentDTO;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:52
 **/
public interface CommentService {
    void insert(Comment comment);

    List<CommentDTO> getCommentListById(Integer id);
}
