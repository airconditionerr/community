package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.NotificationDTO;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.enums.NotificationTypeEnum;
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
 * @Date 2022/9/14 21:22
 **/
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable("id") Integer id,
                          HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterid();
        } else {
            return "redirect:/";
        }

    }

}
