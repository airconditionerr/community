package com.airconditioner.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    /**
     * 自增主键
     */
    private BigInteger id;
    /**
     * 通知的人的 id
     */
    private BigInteger notifier;
    /**
     * 接收消息的人 id
     */
    private BigInteger receiver;
    /**
     * 评论的 问题或评论 的 id
     */
    private BigInteger outerId;
    /**
     * 回复类型：1-评论问题，2-评论评论
     */
    private Integer type;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 是否已读：0-未读，1-已读
     */
    private Integer isRead;
    /**
     * 通知的人的 昵称
     */
    private String notifierName;
    /**
     * 评论的 问题或评论 的 title
     */
    private String outerTitle;

}
