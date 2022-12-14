package com.airconditioner.community.provider;

import com.airconditioner.community.dto.AccessTokenDTO;
import com.airconditioner.community.dto.GitHubIdentificationCodeDTO;
import com.airconditioner.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 12:35
 **/
@Component
public class GithubProvider {
    public AccessTokenDTO getAccessToken(GitHubIdentificationCodeDTO gitHubIdentificationCodeDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //OkHttpClient client = new OkHttpClient().newBuilder()
        //        .connectTimeout(100000, TimeUnit.MILLISECONDS)
        //        .readTimeout(100000, TimeUnit.MILLISECONDS)
        //        .build();
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(gitHubIdentificationCodeDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            String token_type = string.split("&")[2].split("=")[1];
            return new AccessTokenDTO(token, token_type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(AccessTokenDTO accessTokenDTO) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", accessTokenDTO.getToken_type() + " " + accessTokenDTO.getAccess_token())
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
