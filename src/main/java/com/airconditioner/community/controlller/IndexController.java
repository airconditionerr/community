package com.airconditioner.community.controlller;

import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private QuestionService questionService;

    /**
     * 跳转到 index
     *
     * @param model  model
     * @param page   分页页码
     * @param size   分页页面大小
     * @param search 搜索内容
     * @return
     */
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search) {
        PaginationDTO paginationDTO = questionService.listBySearch(search, page, size);
        model.addAttribute("paginationDTO", paginationDTO);
        model.addAttribute("search", search);
        return "index";
    }

}
