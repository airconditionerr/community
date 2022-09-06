package com.airconditioner.community.service.impl;

import com.airconditioner.community.bean.Question;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.mapper.QuestionMapper;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:29
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PaginationDTO getQuestionList(Integer page, Integer size) {
        // 分页DTO 集合
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.questionCount();
        paginationDTO.setPagination(totalCount, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }


        Integer offset = size * (page - 1);
        // 问题 集合
        List<Question> questionList = questionMapper.getQuestionList(offset, size);
        // 问题DTO 集合
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        // 向 问题DTO 中加入 问题DTO
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestionDTOList(questionDTOList);

        return paginationDTO;
    }
}
