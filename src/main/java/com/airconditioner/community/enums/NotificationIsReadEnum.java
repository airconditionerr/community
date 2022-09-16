package com.airconditioner.community.enums;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:18
 **/
public enum NotificationIsReadEnum {
    UNREAD(0), READ(1);

    private int isRead;

    public int getIsRead() {
        return isRead;
    }

    NotificationIsReadEnum(int isRead) {
        this.isRead = isRead;
    }
}
