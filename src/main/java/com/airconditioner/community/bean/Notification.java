package com.airconditioner.community.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/14 17:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private int id;
    private int notifier;
    private int receiver;
    private int outerid;
    private int type;
    private Timestamp gmtCreate;
    private int status;
    private String notifierName;
    private String outerTitle;

}
