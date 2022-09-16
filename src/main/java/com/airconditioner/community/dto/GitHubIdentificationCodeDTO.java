package com.airconditioner.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 12:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitHubIdentificationCodeDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
}
