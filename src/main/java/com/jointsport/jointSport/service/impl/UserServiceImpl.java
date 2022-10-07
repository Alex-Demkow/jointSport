package com.jointsport.jointSport.service.impl;

import com.jointsport.jointSport.dto.LoginInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.dto.UserSearchInfo;
import com.jointsport.jointSport.model.User;
import com.jointsport.jointSport.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jointsport.jointSport.repository.UserRepository;
import com.jointsport.jointSport.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service//что делает эта аннотация?
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(UserBean userBean) {

        return repository.save(UserMapper.userBeanToUser(userBean));
    }

    @Override
    public User updateUser(UserBean userBean) {

        return repository.save(UserMapper.userBeanToUser(userBean));
    }

    @Override
    public List<User> getAllFriends() {
        return null;// что здесь???
    }

    @Override
    public User getUserById(Integer id) {
        return repository.getById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public UserBean logIn(LoginInfo info) {
        if (Objects.nonNull(info) && info.getEmail() != null && info.getPassword() != null) {
            User logInUser = repository.findUserByEmail(info.getEmail());

            if (logInUser == null) {
                return new UserBean("User not found");
            }
//            if (logInUser.getEmail().equals(info.getEmail())) {
//
//                return UserMapper.userToUserBean(logInUser);
//            } else {
//                return new UserBean("Invalid password");
//            }
            if (logInUser.getPassword().equals(info.getPassword())) {

                return UserMapper.userToUserBean(logInUser);
            } else {
                return new UserBean("Invalid password");
            }
        }
        return new UserBean("Login or password not entered");

    }

    @Override
    public void logOut() {

    }

    @Override
    public List<User> getAllUsers(UserSearchInfo search) {
        boolean isNormalObjetc = false;
        List<User> searchListUsers = new ArrayList<>();

        List<User> userList = repository.findAll();
        for (User user : userList) {
            if (search.getName() == null || user.getName().equals(search.getName())) {
                isNormalObjetc = true;
            }
            if (search.getLastName() == null || user.getLastName().equals(search.getLastName())) {
                isNormalObjetc = true;
            }
            if (isNormalObjetc) {
                searchListUsers.add(user);
            }
        }


        return searchListUsers;
    }

}
