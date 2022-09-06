package com.airconditioner.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 14:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenDTO {
    private String access_token;
    private String token_type;
}
