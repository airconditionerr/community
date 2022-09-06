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

    public void publishQuestion(Question question);

    List<Question> getQuestionList(Integer offset, Integer size);

    Integer questionCount();
}
