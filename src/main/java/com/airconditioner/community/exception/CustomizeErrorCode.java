package com.airconditioner.community.exception;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 14:13
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不存在,要不要换个试试？");

    @Override
    public String getMessage() {
        return message;
    }
    private String message;
    CustomizeErrorCode(String message){
        this.message = message;
    }

}
