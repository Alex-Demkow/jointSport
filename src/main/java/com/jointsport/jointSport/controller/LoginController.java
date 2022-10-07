package com.jointsport.jointSport.controller;

import com.jointsport.jointSport.dto.LoginInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.User;
import com.jointsport.jointSport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService service;


    @GetMapping("/logout")
    public String logOut(){
//        service.logOut();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String logInPage(@Valid LoginInfo info,  BindingResult bindingResult, Model model) {
        //LoginInfo info = new LoginInfo();
        model.addAttribute("info", info);
        return "login";//login.html
    }
    @PostMapping("/login")
    public String logIn(@ModelAttribute ("info") @Valid LoginInfo info, BindingResult bindingResult, Model model){

        UserBean userBean = service.logIn(info);
//        if(bindingResult.hasErrors())
//            return "login";
        if(userBean.getMessage() != null){

            info = new LoginInfo();
            model.addAttribute("info", info);
//            FieldError error = new FieldError("info", "email", "sdf");
            bindingResult.addError(new FieldError("info", "email", "sdf"));
            return "login";
        }

        model.addAttribute("userBean", userBean);
        return "userForm";
    }

//    @PostMapping("/login")
//    public String logIn(LoginInfo info, Model model){
//        UserBean userBean = service.logIn(info);
//        if(userBean.getMessage() != null){
//            info = new LoginInfo();
//            model.addAttribute("info", info);
//            model.addAttribute("errorMessage", userBean.getMessage());
//            return "login";
//        }
//
//        model.addAttribute("userBean", userBean);
//        return "userForm";
//    }
}
