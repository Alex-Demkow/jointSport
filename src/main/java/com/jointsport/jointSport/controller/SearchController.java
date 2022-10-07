package com.jointsport.jointSport.controller;

import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.StatusEvent;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.model.User;
import com.jointsport.jointSport.service.EventService;
import com.jointsport.jointSport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


@RestController
public class SearchController {

    @Autowired
    private EventService eventService;
    private UserService userService;






}
