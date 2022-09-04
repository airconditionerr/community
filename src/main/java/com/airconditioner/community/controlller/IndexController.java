package com.airconditioner.community.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author AirConditioner
 * @Date 2022/9/3 16:17
 **/
@Controller
public class IndexController {


    @GetMapping({"/", "/index"})
    public String index(){
        return "index";
    }

}
