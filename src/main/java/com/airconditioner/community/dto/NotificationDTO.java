package com.airconditioner.community.dto;

import lombok.Data;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:41
 **/
@Data
public class NotificationDTO {

    private int id;
    private int gmtCreate;
    private int status;
    private Integer notifier;
    private String notifierName;
    private String outerTitle;
    private Integer outerid;
    private String typeName;
    private Integer type;


}
