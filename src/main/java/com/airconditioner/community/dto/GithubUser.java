package com.airconditioner.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author AirConditoner
 * @Date 2022/9/4 12:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
