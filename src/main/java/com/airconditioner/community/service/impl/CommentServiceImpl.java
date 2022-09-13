package com.airconditioner.community.service.impl;

import com.airconditioner.community.bean.Comment;
import com.airconditioner.community.bean.Question;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.CommentDTO;
import com.airconditioner.community.enums.CommentTypeEnum;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.exception.CustomizeException;
import com.airconditioner.community.mapper.CommentMapper;
import com.airconditioner.community.mapper.QuestionMapper;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:52
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            // 回复评论
            Comment dbcoment = commentMapper.selectCommentById(comment.getParentId());
            if (dbcoment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
                Comment parentComment = new Comment();
                parentComment.setId(comment.getParentId());
                commentMapper.incCommentCount(comment);
            }
        } else {
            // 回复问题
            Question question = questionMapper.selectQuestionById(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incQuestionCommentCount(question);
        }
    }

    @Override
    public List<CommentDTO> listByTargetId(Integer id, CommentTypeEnum type) {
        // 获取评论列表
        List<Comment> comments = commentMapper.selectCommentList(id, type.getType());
        // 评论列表判空
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        // 获取去重的评论人
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        // 获取评论人并转换为 Map
        List<User> users = new ArrayList<>();
        for (Integer userId : userIds){
            users.add(userMapper.selectUserById(userId));
        }
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
