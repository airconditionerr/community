package com.airconditioner.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    /**
     * 自增主键
     */
    private BigInteger id;
    /**
     * 评论的问题或评论的id
     */
    private BigInteger parentId;
    /**
     * 回复类型：1-评论问题，2-评论评论
     */
    private Integer type;
    /**
     * 评论者id
     */
    private BigInteger commentator;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 修改时间
     */
    private Timestamp gmtModified;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论数
     */
    private Integer commentCount;
}
