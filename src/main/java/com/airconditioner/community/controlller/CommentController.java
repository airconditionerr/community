package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.Comment;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.CommentCreateDTO;
import com.airconditioner.community.dto.ResultDTO;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:24
 **/
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论
     * @param commentCreateDTO    (parentId, content, type)
     * @param session   (user)
     * @return
     */
    @PostMapping("/comment")
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreateDTO == null || commentCreateDTO.getContent() == null || "".equals(commentCreateDTO.getContent())){
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(new Timestamp(System.currentTimeMillis()));
        comment.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }

}
