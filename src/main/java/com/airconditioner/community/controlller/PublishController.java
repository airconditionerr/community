package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.Question;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 10:19
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String toPublish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            HttpSession session,
                            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        // 非空判断
        if (title == null || "".equals(title)){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User) session.getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        question.setGmtModified(new Timestamp(System.currentTimeMillis()));

        questionMapper.publishQuestion(question);
        return "redirect:/";
    }

}
