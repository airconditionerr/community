package com.airconditioner.community.controlller;

import com.airconditioner.community.entity.Comment;
import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.CommentCreateDTO;
import com.airconditioner.community.dto.CommentDTO;
import com.airconditioner.community.dto.ResultDTO;
import com.airconditioner.community.enums.CommentTypeEnum;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

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
        commentService.insert(comment, user);

        return ResultDTO.okOf();
    }


    /**
     * 二级评论
     * @return
     */
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentDTO>> comments(@PathVariable("id") BigInteger id){
        List<CommentDTO> commentDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOS);
    }

}
