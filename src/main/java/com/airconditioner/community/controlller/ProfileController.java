package com.airconditioner.community.controlller;

import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.service.NotificationService;
import com.airconditioner.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 23:58
 **/
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    /**
     * 跳转到 profile
     * @param action    ("questions" || "replies")
     * @param session   session
     * @param model model
     * @param page  分页页码
     * @param size  分页页面大小
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          HttpSession session,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "10") Integer size) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
            PaginationDTO paginationDTO = questionService.listByUserId(user.getId(), page, size);
            model.addAttribute("paginationDTO", paginationDTO);
        } else if ("replies".equals(action)) {

            PaginationDTO paginationDTO = notificationService.listByUserId(user.getId(), page, size);
            Integer unreadCount = notificationService.countUnreadByUserId(user.getId());
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
            model.addAttribute("paginationDTO", paginationDTO);
        }




        return "profile";
    }

}
