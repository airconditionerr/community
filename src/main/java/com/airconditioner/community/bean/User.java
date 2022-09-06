package com.airconditioner.community.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 15:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String avatarUrl;
}
