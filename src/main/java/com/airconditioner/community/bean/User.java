package com.airconditioner.community.bean;

import java.sql.Timestamp;

/**
 * @Author AirConditioner
 * @Date 2022/9/4 15:58
 **/
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public User() {
    }

    public User(Integer id, String accountId, String name, String token, Timestamp gmtCreate, Timestamp getModified) {
        this.id = id;
        this.name = name;
        this.accountId = accountId;
        this.token = token;
        this.gmtCreate = gmtCreate;
        this.gmtModified = getModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
