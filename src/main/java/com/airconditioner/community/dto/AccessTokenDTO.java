package com.airconditioner.community.dto;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 14:35
 **/
public class AccessTokenDTO {
    private String access_token;
    private String token_type;

    public AccessTokenDTO() {
    }

    public AccessTokenDTO(String access_token, String token_type) {
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
