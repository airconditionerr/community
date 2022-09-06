package com.airconditioner.community.dto;

import com.airconditioner.community.bean.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
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
    private User user;
}
