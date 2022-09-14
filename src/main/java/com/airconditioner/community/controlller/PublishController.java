package com.airconditioner.community.controlller;

import com.airconditioner.community.bean.Question;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.cache.TagCache;
import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.exception.CustomizeException;
import com.airconditioner.community.service.QuestionService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 10:19
 **/
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**
     * 跳转到 问题编辑 页面
     * @param id    问题id
     * @param model id、title、description、tag
     * @return
     */
    @GetMapping("/publish/{id}")
    public String toEdit(@PathVariable("id") Integer id,
                         Model model) {
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id", questionDTO.getId());
        model.addAttribute("tagDTOs", TagCache.get());

        return "publish";
    }

    /**
     * 跳转到 问题发布 页面
     * @return
     */
    @GetMapping("/publish")
    public String toPublish(Model model) {
        model.addAttribute("tagDTOs", TagCache.get());
        return "publish";
    }

    /**
     * 发布问题
     * @param title 问题标题
     * @param description   问题描述
     * @param tag   问题标签
     * @param session   会话
     * @param model title、description、tag
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpSession session,
                            Model model) {

        // 出错时自动填充表单内容
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tagDTOs", TagCache.get());


        // 非空判断
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if (!StringUtils.isNullOrEmpty(invalid)){
            model.addAttribute("error", "输入非法标签");
            return "publish";
        }

        // 登录判断
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.publishQuestion(question);
        return "redirect:/";
    }


    /**
     * 编辑问题
     * @param title 问题标题
     * @param description   问题描述
     * @param tag   问题标签
     * @param id    问题id
     * @param session   会话
     * @param model title、description、tag
     * @return
     */
    @PutMapping("/publish/{id}")
    public String edit(@RequestParam("title") String title,
                       @RequestParam("description") String description,
                       @RequestParam("tag") String tag,
                       @PathVariable("id") Integer id,
                       HttpSession session,
                       Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tagDTOs", TagCache.get());

        // 非空判断
        if (title == null || "".equals(title)) {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || "".equals(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if (!StringUtils.isNullOrEmpty(invalid)){
            model.addAttribute("error", "输入非法标签");
            return "publish";
        }

        // 登录判断
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);

        // 编辑是否成功判断
        int updateNum = questionService.editQuestion(question);
        if (updateNum != 1){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        return "redirect:/";
    }

}
