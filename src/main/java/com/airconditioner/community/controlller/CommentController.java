package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.Comment;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.CommentDTO;
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
     * @param commentDTO    (parentId, content, type)
     * @param session   (user)
     * @return
     */
    @PostMapping("/comment")
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(new Timestamp(System.currentTimeMillis()));
        comment.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        comment.setCommentator(user.getId());
        comment.setLikeCount(0);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }

}
