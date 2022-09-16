package com.airconditioner.community.mapper;

import com.airconditioner.community.entity.Question;
import com.airconditioner.community.dto.QuestionQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author AirConditioner
 * @Date 2022/9/5 15:47
 **/
@Mapper
public interface QuestionMapper {

    void insert(Question question);

    int updateQuestion(Question question);

    List<Question> listBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> getByUserId(BigInteger userId, Integer offset, Integer size);

    Integer countByUserId(BigInteger userId);

    Question getById(BigInteger id);



    void incViewCount(BigInteger id);

    void incCommentCount(Question question);

    List<Question> listRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);
}
