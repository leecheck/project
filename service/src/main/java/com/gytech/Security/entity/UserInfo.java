package com.gytech.Security.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by LQ on 2019/9/2.
 * com.gytech.Security.entity
 */
public class UserInfo {

    private Long userId;

    private String userName;

    private String alasName;

    private String orgName;

    private Long orgId;

    private List<String> powers;

    private List<String> roles;

    private Date timestamp = new Date();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAlasName() {
        return alasName;
    }

    public void setAlasName(String alasName) {
        this.alasName = alasName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
