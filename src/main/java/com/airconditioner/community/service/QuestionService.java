package com.airconditioner.community.service;

import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.dto.QuestionDTO;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:29
 **/
public interface QuestionService {
    PaginationDTO getQuestionList(Integer page, Integer size);

    PaginationDTO getQuestionList(Integer userId, Integer page, Integer size);

    QuestionDTO getQuestionById(Integer id);

    void incViewCount(Integer id);
}
