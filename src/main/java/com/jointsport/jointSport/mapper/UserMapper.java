package com.jointsport.jointSport.mapper;

import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.model.User;

public class UserMapper {



    public static UserBean userToUserBean(User user){
        UserBean userBean = new UserBean();
        userBean.setId(user.getId());
        userBean.setName(user.getName());
        userBean.setLastName(user.getLastName());
        userBean.setEmail(user.getEmail());
        userBean.setPassword(user.getPassword());
        userBean.setEventsList(user.getEvents());

                return userBean;
    }

    public static User userBeanToUser(UserBean userBean){
        User user = new User();
        user.setId(userBean.getId());
        user.setName(userBean.getName());
        user.setLastName(userBean.getLastName());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        user.setEvents(userBean.getEventsList());

        return user;
    }
}
