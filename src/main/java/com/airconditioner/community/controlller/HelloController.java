package com.airconditioner.community.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author AirConditoner
 * @Date 2022/9/3 16:17
 **/
@Controller
public class HelloController {


    @GetMapping(value = "/hello")
    public String hello(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello";
    }

}
