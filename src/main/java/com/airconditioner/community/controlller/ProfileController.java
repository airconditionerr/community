package com.airconditioner.community.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 23:58
 **/
@Controller
public class ProfileController {

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action,
                          Model model){
        if ("questions".equals(action)){
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        }
        return "profile";
    }

}
