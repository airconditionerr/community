package com.airconditioner.community.controlller;

import com.airconditioner.community.dto.CommentDTO;
import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.enums.CommentTypeEnum;
import com.airconditioner.community.service.CommentService;
import com.airconditioner.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/8 16:09
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectQuestionRelated(questionDTO);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        // 累加评论
        questionService.incViewCount(id);
        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }

}
