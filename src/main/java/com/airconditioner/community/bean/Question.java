package com.airconditioner.community.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 15:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer followCount;
    private String tag;
}
