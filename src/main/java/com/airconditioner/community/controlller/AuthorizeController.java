package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.AccessTokenDTO;
import com.airconditioner.community.dto.GithubUser;
import com.airconditioner.community.dto.TempCodeDTO;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.provider.GithubProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.sql.Timestamp;
import java.util.UUID;

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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession session,
                           HttpServletResponse response) {
        TempCodeDTO tempCodeDTO = new TempCodeDTO(clientId, client_secret, code, redirect_uri, state);
        AccessTokenDTO accessTokenDTO = githubProvider.getAccessToken(tempCodeDTO);
        log.info("accessToken:" + accessTokenDTO.getAccess_token());
        log.info("accessToken_Type:" + accessTokenDTO.getToken_type());
        GithubUser githubUser = githubProvider.getGithubUser(accessTokenDTO);
        log.info("githubUser.name:" + githubUser.getName());
        if (githubUser != null){
            // 登录成功 => cookie & session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            user.setGmtModified(user.getGmtCreate());
            userMapper.registerByGithub(user);

            response.addCookie(new Cookie("token", token));

            return "redirect:/";
        } else {
            // 登陆失败
            return "redirect:/";
        }
    }


}
