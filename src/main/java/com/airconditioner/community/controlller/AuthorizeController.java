package com.airconditioner.community.controlller;

import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.AccessTokenDTO;
import com.airconditioner.community.dto.GithubUser;
import com.airconditioner.community.dto.GitHubIdentificationCodeDTO;
import com.airconditioner.community.provider.GithubProvider;
import com.airconditioner.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code, HttpServletResponse response) {
        GitHubIdentificationCodeDTO gitHubIdentificationCodeDTO = new GitHubIdentificationCodeDTO(clientId, client_secret, code, redirect_uri);
        AccessTokenDTO accessTokenDTO = githubProvider.getAccessToken(gitHubIdentificationCodeDTO);
        GithubUser githubUser = githubProvider.getGithubUser(accessTokenDTO);
        if (githubUser != null && githubUser.getId() != null) {
            // 登录成功 => cookie & session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.loginByGithub(user);
            response.addCookie(new Cookie("token", token));

            log.info("登录成功");
            return "redirect:/";
        } else {
            // 登陆失败
            log.error("登录失败");
            return "redirect:/";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session,
                         HttpServletResponse response) {
        session.removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }


}
