package com.airconditioner.community.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 12:42
 **/
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
