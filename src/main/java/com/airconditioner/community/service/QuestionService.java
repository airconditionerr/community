package com.airconditioner.community.service;

import com.airconditioner.community.dto.QuestionDTO;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:29
 **/
public interface QuestionService {
    List<QuestionDTO> getQuestionList();

}
