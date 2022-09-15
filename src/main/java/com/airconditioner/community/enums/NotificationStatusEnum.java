package com.airconditioner.community.enums;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:18
 **/
public enum NotificationStatusEnum {
    UNREAD(0), READ(1);

    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
