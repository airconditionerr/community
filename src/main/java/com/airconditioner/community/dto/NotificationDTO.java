package com.airconditioner.community.dto;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:41
 **/
@Data
public class NotificationDTO {

    private BigInteger id;
    private Timestamp gmtCreate;
    private Integer isRead;
    private BigInteger notifier;
    private String notifierName;
    private String outerTitle;
    private BigInteger outerId;
    private String typeName;
    private Integer type;


}
