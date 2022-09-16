package com.airconditioner.community.service;

import com.airconditioner.community.entity.Question;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.dto.QuestionDTO;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:29
 **/
public interface QuestionService {
    /**
     * 获取 搜索问题 集合
     *
     * @param search 搜索内容
     * @param page   分页页码
     * @param size   分页页面大小
     * @return
     */
    PaginationDTO listBySearch(String search, Integer page, Integer size);

    PaginationDTO listByUserId(BigInteger userId, Integer page, Integer size);

    QuestionDTO getById(BigInteger id);

    void incViewCount(BigInteger id);

    void publishQuestion(Question question);

    int editQuestion(Question question);

    List<QuestionDTO> selectQuestionRelated(QuestionDTO questionDTO);
}
