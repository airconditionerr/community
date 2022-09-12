package com.airconditioner.community.mapper;

import com.airconditioner.community.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:29
 **/
@Mapper
public interface CommentMapper {


    void insert(Comment comment);

    Comment selectCommentByParentId(Integer parentId);
}
