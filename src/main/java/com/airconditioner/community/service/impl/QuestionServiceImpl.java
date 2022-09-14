package com.airconditioner.community.service.impl;

import com.airconditioner.community.bean.Question;
import com.airconditioner.community.bean.User;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.exception.CustomizeErrorCode;
import com.airconditioner.community.exception.CustomizeException;
import com.airconditioner.community.mapper.QuestionMapper;
import com.airconditioner.community.mapper.UserMapper;
import com.airconditioner.community.service.QuestionService;
import com.mysql.jdbc.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author AirConditioner
 * @Date 2022/9/6 15:29
 **/
@Slf4j
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
        Integer totalPage;
        Integer totalCount = questionMapper.countQuestion();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        // 问题 集合
        List<Question> questionList = questionMapper.selectQuestionLimited(offset, size);
        // 问题DTO 集合
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        // 向 问题DTO 中加入 问题DTO
        for (Question question : questionList) {
            User user = userMapper.selectUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestionDTOList(questionDTOList);

        return paginationDTO;
    }

    @Override
    public PaginationDTO getQuestionList(Integer userId, Integer page, Integer size) {
        // 分页DTO 集合
        PaginationDTO paginationDTO = new PaginationDTO();


        Integer totalPage;
        Integer totalCount = questionMapper.countQuestionByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);


        Integer offset = size * (page - 1);
        // 问题 集合
        List<Question> questionList = questionMapper.selectQuestionByUserId(userId, offset, size);
        // 问题DTO 集合
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        // 向 问题DTO 中加入 问题DTO
        for (Question question : questionList) {
            User user = userMapper.selectUserById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestionDTOList(questionDTOList);

        return paginationDTO;
    }

    @Override
    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectQuestionById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectUserById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 浏览数增长
     * @param id    问题id
     */
    @Override
    public void incViewCount(Integer id) {
        questionMapper.incQuestionViewCount(id);
    }

    /**
     * 发布问题
     * @param question
     */
    @Override
    public void publishQuestion(Question question) {
        question.setViewCount(0);
        question.setFollowCount(0);
        question.setCommentCount(0);
        question.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        question.setGmtModified(new Timestamp(System.currentTimeMillis()));
        questionMapper.insertQuestion(question);
    }

    /**
     * 编辑问题
     * @param question
     * @return
     */
    @Override
    public int editQuestion(Question question) {
        question.setGmtModified(new Timestamp(System.currentTimeMillis()));
        int updateNum = questionMapper.updateQuestion(question);
        return updateNum;
    }

    @Override
    public List<QuestionDTO> selectQuestionRelated(QuestionDTO queryDTO) {
        if (StringUtils.isNullOrEmpty(queryDTO.getTag())){
            return new ArrayList<>();
        }
        String[] tags = queryDTO.getTag().split(",|，");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionMapper.selectQuestionRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }


}
