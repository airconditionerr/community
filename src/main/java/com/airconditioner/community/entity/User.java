package com.airconditioner.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 15:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 自增主键
     */
    private BigInteger id;
    /**
     * 对应 Github Id
     */
    private String accountId;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * token
     */
    private String token;
    /**
     * 创建时间
     */
    private Timestamp gmtCreate;
    /**
     * 修改时间
     */
    private Timestamp gmtModified;
    /**
     * 头像 url（对应Github头像）
     */
    private String avatarUrl;
}
