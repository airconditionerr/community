package com.airconditioner.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 15:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    /**
     * 自增主键
     */
    private BigInteger id;
    /**
     * 问题名称
     */
    private String title;
    /**
     * 问题描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 修改时间
     */
    private Timestamp gmtModified;
    /**
     * 提问者 id
     */
    private BigInteger creator;
    /**
     * 当前问题评论数
     */
    private Integer commentCount;
    /**
     * 当前问题浏览数
     */
    private Integer viewCount;
    /**
     * 问题标签
     */
    private String tag;
}


