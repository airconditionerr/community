package com.airconditioner.community.controlller;

import com.airconditioner.community.dto.AccessTokenDTO;
import com.airconditioner.community.dto.GithubUser;
import com.airconditioner.community.dto.TempCodeDTO;
import com.airconditioner.community.provider.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author AirConditoner
 * @Date 2022/9/4 12:29
 **/
@Controller
@Slf4j
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state) {
        TempCodeDTO tempCodeDTO = new TempCodeDTO(clientId, client_secret, code, redirect_uri, state);
        AccessTokenDTO accessTokenDTO = githubProvider.getAccessToken(tempCodeDTO);
        log.info("accessToken:" + accessTokenDTO.getAccess_token());
        log.info("accessToken_Type:" + accessTokenDTO.getToken_type());
        GithubUser githubUser = githubProvider.getGithubUser(accessTokenDTO);
        log.info("githubUser.name:" + githubUser.getName());
        return "index";
    }


}
