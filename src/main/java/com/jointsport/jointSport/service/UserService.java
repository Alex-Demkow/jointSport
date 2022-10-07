package com.jointsport.jointSport.service;

import com.jointsport.jointSport.dto.LoginInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.dto.UserSearchInfo;
import com.jointsport.jointSport.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    User createUser(UserBean userBean);

    User updateUser(UserBean userBean);

    List<User> getAllFriends();

    User getUserById(Integer id);


    void deleteUserById(Integer id);

    UserBean logIn(LoginInfo info);

    void logOut();

    List<User> getAllUsers(UserSearchInfo search);
}
