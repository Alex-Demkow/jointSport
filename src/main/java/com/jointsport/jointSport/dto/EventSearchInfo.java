package com.jointsport.jointSport.dto;

import com.jointsport.jointSport.model.Chat;
import com.jointsport.jointSport.model.StatusEvent;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.model.User;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

public class EventSearchInfo {


    private TypeEvent type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String address;
    private String userName;
    private String userLastName;
    private Integer minNumberOfUsers;
    private Integer maxNumberOfUsers;

    public TypeEvent getType() {
        return type;
    }

    public void setType(TypeEvent type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getMinNumberOfUsers() {
        return minNumberOfUsers;
    }

    public void setMinNumberOfUsers(Integer minNumberOfUsers) {
        this.minNumberOfUsers = minNumberOfUsers;
    }

    public Integer getMaxNumberOfUsers() {
        return maxNumberOfUsers;
    }

    public void setMaxNumberOfUsers(Integer maxNumberOfUsers) {
        this.maxNumberOfUsers = maxNumberOfUsers;
    }
}
