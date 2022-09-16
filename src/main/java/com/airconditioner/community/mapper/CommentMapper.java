package com.airconditioner.community.mapper;

import com.airconditioner.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:29
 **/
@Mapper
public interface CommentMapper {


    void insert(Comment comment);

    Comment getById(BigInteger id);

    List<Comment> listById(BigInteger id, Integer type);

    void incCommentCount(Comment comment);
}
