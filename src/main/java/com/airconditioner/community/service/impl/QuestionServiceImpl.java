package com.airconditioner.community.service.impl;

import com.airconditioner.community.entity.Question;
import com.airconditioner.community.entity.User;
import com.airconditioner.community.dto.PaginationDTO;
import com.airconditioner.community.dto.QuestionDTO;
import com.airconditioner.community.dto.QuestionQueryDTO;
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

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    /**
     * 获取 搜索问题 集合
     * @param search 搜索内容
     * @param page   分页页码
     * @param size   分页页面大小
     * @return
     */
    @Override
    public PaginationDTO listBySearch(String search, Integer page, Integer size) {
        // search 判空 & -> regex
        if (!StringUtils.isNullOrEmpty(search)){
            String[] tags = search.split(" ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = questionMapper.countBySearch(questionQueryDTO);
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
        questionQueryDTO.setOffset(offset);
        questionQueryDTO.setSize(size);
        List<Question> questionList = questionMapper.listBySearch(questionQueryDTO);
        // 问题DTO 集合
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        // 向 问题DTO 中加入 问题DTO
        for (Question question : questionList) {
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    @Override
    public PaginationDTO listByUserId(BigInteger userId, Integer page, Integer size) {
        // 分页DTO 集合
        PaginationDTO paginationDTO = new PaginationDTO();


        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

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
        if (page < 1) {
            page = 1;
        }

        paginationDTO.setPagination(totalPage, page);


        Integer offset = size * (page - 1);
        // 问题 集合
        List<Question> questionList = questionMapper.getByUserId(userId, offset, size);
        // 问题DTO 集合
        List<QuestionDTO> questionDTOList = new ArrayList<>();


        // 向 问题DTO 中加入 问题DTO
        for (Question question : questionList) {
            User user = userMapper.getById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    @Override
    public QuestionDTO getById(BigInteger id) {
        Question question = questionMapper.getById(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.getById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 浏览数增长
     * @param id    问题id
     */
    @Override
    public void incViewCount(BigInteger id) {
        questionMapper.incViewCount(id);
    }

    /**
     * 发布问题
     * @param question
     */
    @Override
    public void publishQuestion(Question question) {
        question.setViewCount(0);
        question.setCommentCount(0);
        question.setGmtCreate(new Timestamp(System.currentTimeMillis()));
        question.setGmtModified(new Timestamp(System.currentTimeMillis()));
        questionMapper.insert(question);
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

        List<Question> questions = questionMapper.listRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }


}
