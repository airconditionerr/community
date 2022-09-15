package com.airconditioner.community.dto;

import lombok.Data;

/**
 * @Author AirConditioner
 * @Date 2022/9/15 13:25
 **/
@Data
public class QuestionQueryDTO {

    private String search;
    private Integer offset;
    private Integer size;

}
