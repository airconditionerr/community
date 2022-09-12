package com.airconditioner.community.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Integer likeCount;
    private String content;
    private Integer commentCount;


}
