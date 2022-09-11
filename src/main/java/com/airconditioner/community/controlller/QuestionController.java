package com.airconditioner.community.controlller;

import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.mapper.QuestionMapper;
import com.airconditioner.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author AirConditioner
 * @Date 2022/9/8 16:09
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getQuestionById(id);
        // 累加评论
        questionService.incViewCount(id);
        model.addAttribute("questionDTO", questionDTO);
        return "question";
    }

}
