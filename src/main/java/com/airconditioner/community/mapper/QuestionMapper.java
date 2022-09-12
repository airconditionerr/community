package com.airconditioner.community.mapper;

import com.airconditioner.community.bean.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 15:47
 **/
@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    List<Question> selectQuestionLimited(Integer offset, Integer size);

    Integer countQuestion();

    List<Question> selectQuestionByUserId(Integer userId, Integer offset, Integer size);

    Integer countQuestionByUserId(Integer userId);

    Question selectQuestionById(Integer id);

    int updateQuestion(Question question);

    void incQuestionViewCount(Integer id);

    void incQuestionCommentCount(Question question);

}
