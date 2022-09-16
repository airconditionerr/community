package com.airconditioner.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 20:26
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {
    private BigInteger parentId;
    private String content;
    private Integer type;
}
