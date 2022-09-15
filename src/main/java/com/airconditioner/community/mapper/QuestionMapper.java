package com.airconditioner.community.mapper;

import com.airconditioner.community.bean.Question;
import com.airconditioner.community.dto.QuestionQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 15:47
 **/
@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    List<Question> selectQuestionBySearch(QuestionQueryDTO questionQueryDTO);

    Integer countQuestion();

    List<Question> selectQuestionByUserId(Integer userId, Integer offset, Integer size);

    Integer countQuestionByUserId(Integer userId);

    Question selectQuestionById(Integer id);

    int updateQuestion(Question question);

    void incQuestionViewCount(Integer id);

    void incQuestionCommentCount(Question question);

    List<Question> selectQuestionRelated(Question question);

    Integer countQuestionBySearch(QuestionQueryDTO questionQueryDTO);
}
