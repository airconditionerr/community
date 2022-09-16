package com.airconditioner.community.dto;

import com.airconditioner.community.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/12 19:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Integer likeCount;
    private String content;
    private Integer commentCount;
    private User user;
}
