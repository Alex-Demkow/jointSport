package com.jointsport.jointSport.controller;


import com.jointsport.jointSport.dto.LoginInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.dto.UserSearchInfo;
import com.jointsport.jointSport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.jointsport.jointSport.service.UserService;

import javax.validation.Valid;
import java.util.List;

import static com.jointsport.jointSport.mapper.UserMapper.userToUserBean;

@Controller//что делает эта аннотация?
public class UserController {

    @Autowired//что делает эта аннотация?
    private UserService service;

    @GetMapping("/create-user")
    public String createUserPage(@ModelAttribute ("userBean") UserBean userBean, Model model) {
        model.addAttribute("userBean", userBean);
        return "createUser";//createUser.html
    }
    @PostMapping(value = "/create-user")
    public String createUser(@ModelAttribute ("userBean") @Valid UserBean userBean,
                             BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "createUser";
        User user = service.createUser(userBean);
        if (user != null) {
            model.addAttribute("userBean", user);
            return "userForm";
        } else {
            model.addAttribute("userBean", user);
            return "redirect:/createUser";
        }
    }

    @GetMapping("/update-user/{userId}")
    public String updateUserPage(@PathVariable(name = "userId") Integer userId, Model model) {
        UserBean userBean = userToUserBean(service.getUserById(userId));
        model.addAttribute("userBean", userBean);
        return "updateUser";
    }

    @PostMapping(value = "/update-user/{userId}")
    public String updateUser(@PathVariable(name = "userId") Integer userId,
                             @RequestParam String name,
                             @RequestParam String lastName,
                             @RequestParam String email,
                             @RequestParam String password,
                             @Valid UserBean userBean, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "updateUser";
        userBean = userToUserBean(service.getUserById(userId));
        userBean.setName(name);
        userBean.setLastName(lastName);
        userBean.setEmail(email);
        userBean.setPassword(password);
        User user = service.updateUser(userBean);
        if (user != null) {
            model.addAttribute("userBean", user);
            return "userForm";
        } else {
            model.addAttribute("userBean", user);
            return "redirect:/updateUser";
        }
    }

    @GetMapping(value = "/delete-user-by-id/{userId}")
    public String deleteUserById(@PathVariable(name = "userId") Integer userId) {
//        public String deleteUser(@PathVariable(name = "userId") Integer userId,Model model) {

        service.deleteUserById(userId);
        return "deleteUser";
//        return "redirect:/login";
    }

    @GetMapping("/get-all-friends")//что делает эта аннотация? что делает запись в скобках?
    public List<User> getAllFriends() {
        return service.getAllFriends();
    }

    @GetMapping("/get_user_by_id/{userId}")//что делает эта аннотация? что делает запись в скобках?
    public String getUserById(@PathVariable (name = "userId") Integer userId, Model model) {
        UserBean userBean = userToUserBean(service.getUserById(userId));
        model.addAttribute("userBean", userBean);
        return "userForm";
    }

    @GetMapping("/get_user_by_id_in")//что делает эта аннотация? что делает запись в скобках?
    public User getUserByIdIn(@PathVariable Integer id) {
        return service.getUserById(id);
    }

    @GetMapping("/get-users")//что делает эта аннотация? что делает запись в скобках?
    public List<User> getAllUsers(UserSearchInfo search) {
        return service.getAllUsers(search);
    }


//    @GetMapping("/get_user_by_name")
//    public User getUserByName(@PathVariable String name){
//        return service.getUserByName(name);
//    }
//
//    @GetMapping("/get_user_by_lastName")
//    public User getUserByLastName(@PathVariable String lastName){
//        return service.getUserByLastName(lastName);
//    }
//
//    @GetMapping("/add_frind")
//    public boolean addFrind(){
//        return service.addFrind();
//    }

}
