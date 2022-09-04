package com.airconditioner.community.mapper;

import com.airconditioner.community.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 16:13
 **/
@Mapper
public interface UserMapper {
    void insert(User user);
}
