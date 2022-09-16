package com.airconditioner.community.controlller;

import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.NotificationDTO;
import com.airconditioner.community.enums.NotificationTypeEnum;
import com.airconditioner.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 21:22
 **/
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 已读通知
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/notification/{id}")
    public String read(@PathVariable("id") BigInteger id,
                          HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }

    }

}
