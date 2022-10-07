package com.jointsport.jointSport.dto;

import com.jointsport.jointSport.model.Event;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.ManyToMany;
import java.util.List;

public class UserSearchInfo {

    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
