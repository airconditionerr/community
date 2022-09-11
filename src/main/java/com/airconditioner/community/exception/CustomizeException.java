package com.airconditioner.community.exception;

/**
 * @Author AirConditioner
 * @Date 2022/9/11 14:01
 **/
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();
    }
    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
