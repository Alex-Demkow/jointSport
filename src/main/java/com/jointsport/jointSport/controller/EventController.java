package com.jointsport.jointSport.controller;


import com.jointsport.jointSport.dto.EventBean;
import com.jointsport.jointSport.dto.EventSearchInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.mapper.EventMapper;
import com.jointsport.jointSport.mapper.UserMapper;
import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.StatusEvent;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.model.User;
import com.jointsport.jointSport.repository.UserRepository;
import com.jointsport.jointSport.service.EventService;
import com.jointsport.jointSport.service.UserService;
import com.jointsport.jointSport.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.jointsport.jointSport.mapper.EventMapper.eventToEventBean;
import static com.jointsport.jointSport.mapper.UserMapper.userToUserBean;

@Controller
public class EventController {

    @Autowired
    private EventService service;
    @Autowired
    private UserService userService;


    @GetMapping("/create-event/{userId}")
    public String createEventPage(@ModelAttribute("eventBean") EventBean eventBean,
                                  @PathVariable(name = "userId") Integer userId,
                                  Model model) {
        eventBean.setUserId(userId);
        model.addAttribute("eventBean", eventBean);
        return "createEvent";
    }

    @PostMapping(value = "/create-event/{userId}")
    public String createEvent(@PathVariable(name = "userId") Integer userId,
                              @ModelAttribute("eventBean") @Valid EventBean eventBean, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "createEvent";
        eventBean.setUserId(userId);
        Event event = service.createEvent(eventBean);
        eventBean = EventMapper.eventToEventBean(event);
        UserBean userById = UserMapper.userToUserBean(userService.getUserById(userId));
        if (event != null) {
            model.addAttribute("eventBean", eventBean);
            model.addAttribute("userBean", userById);
            return "eventForm";
        } else {
            model.addAttribute("eventBean", eventBean);
            return "redirect:/createEvent";
        }

    }

    @GetMapping("/update-event/{id}/{userId}")
    public String updateEventPage(@PathVariable(name = "id") Integer id, Model model) {
        EventBean eventBean = EventMapper.eventToEventBean(service.getEventById(id));
        model.addAttribute("eventBean", eventBean);
        return "updateEvent";
    }



    @PostMapping(value = "/update-event/{id}/{userId}")
    public String updateEvent(@PathVariable(name = "userId") Integer userId,
                              @PathVariable(name = "id") Integer id,
                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                              @RequestParam Date date,
                              @RequestParam LocalTime time,
                            //  @RequestParam StatusEvent status,
                              @RequestParam String description,
                              @RequestParam String address,
                              @RequestParam Integer numberOfUsers,
                              @RequestParam String typeEvent,
                              Model model) {
        EventBean eventBean = EventMapper.eventToEventBean(service.getEventById(id));
        eventBean.setDate(date);
        eventBean.setTime(time);
        eventBean.setAddress(address);
      //  eventBean.setStatus(StatusEvent.CLOSE_AND_INVISIBLE);
        eventBean.setDescription(description);
        eventBean.setNumberOfUsers(numberOfUsers);
        eventBean.setTypeEvent(typeEvent);
        Event event = service.updateEvent(eventBean);
        eventBean = EventMapper.eventToEventBean(event);
        UserBean userById = UserMapper.userToUserBean(userService.getUserById(userId));
        if (event != null) {
            model.addAttribute("eventBean", eventBean);
            model.addAttribute("userBean", userById);
            return "eventForm";
        } else {
            model.addAttribute("eventBean", eventBean);
            return "redirect:/updateEvent";
        }
    }

    @GetMapping(value = "/delete-event-by-id/{id}/{userId}")
    public String deleteEventById(@PathVariable(name = "id") Integer id,
                                  @PathVariable(name = "userId") Integer userId, Model model) {
        service.deleteEventById(id);

        List<Event> allEventsByUserId = service.getAllEventsByUserId(userId);
        allEventsByUserId.stream()
                .filter(Objects::nonNull)
                .forEach(x -> x.setCreaterUserId(userId));
        EventBean eventBean = new EventBean();
        eventBean.setUserId(userId);
        UserBean  userBeanId= new UserBean();
        userBeanId.setId(userId);
        model.addAttribute("userEvents", allEventsByUserId);
        model.addAttribute("eventBean", eventBean);
        model.addAttribute("userBean", userBeanId);
        return "listEvents";
    }

    @GetMapping("/get-event-by-id/{id}/{userId}")
    public String getEventById(@PathVariable(name = "id") Integer id,
                               @PathVariable(name = "userId") Integer userId, Model model) {
        EventBean eventById = EventMapper.eventToEventBean(service.getEventById(id));
        UserBean userById = UserMapper.userToUserBean(userService.getUserById(userId));
        List<User> userList = eventById.getEventUsers();
        model.addAttribute("eventBean", eventById);
        model.addAttribute("userBean", userById);
        model.addAttribute("userList", userList);
        return "eventForm";
    }

    @GetMapping("/get-events/{userId}")
    public String getAllEvents(@PathVariable(name = "userId") Integer userId, Model model) {
        UserBean userBean = UserMapper.userToUserBean(userService.getUserById(userId));
        model.addAttribute("userBean", userBean);
        EventSearchInfo searchEvents = new EventSearchInfo();
        model.addAttribute("searchEvents", searchEvents);
        return "searchEvents";
    }

    @PostMapping(value = "/get-events/{userId}")
    public String getAllEvents(@PathVariable(name = "userId") Integer userId, UserBean userBean, EventSearchInfo searchEvents, Model model) {

        userBean = UserMapper.userToUserBean(userService.getUserById(userId));
        model.addAttribute("userBean", userBean);

        model.addAttribute("searchListEvents", service.getAllEvents(searchEvents));
        return "listSearchEvents";
    }


    @GetMapping("/get-all-events-by-user-id/{userId}")
    public String getEventsById(@PathVariable(name = "userId") Integer userId, Model model) {
        List<Event> allEventsByUserId = service.getAllEventsByUserId(userId);
        allEventsByUserId.stream()
                .filter(Objects::nonNull)
                .forEach(x -> x.setCreaterUserId(userId));
        EventBean eventBean = new EventBean();
        eventBean.setUserId(userId);
        UserBean userById = UserMapper.userToUserBean(userService.getUserById(userId));
        List<Event> eventsWithUser = service.findAllEventsByUserId(userId);

        model.addAttribute("userEvents", allEventsByUserId);
        model.addAttribute("eventBean", eventBean);
        model.addAttribute("userBean", userById);
        model.addAttribute("eventsWithUser", eventsWithUser);
        return "listEvents";
    }


    @GetMapping(value = "/add-user-to-event/{id}/{userId}")
    public String addUserToEvent(@PathVariable(name = "id") Integer id,
                                 @PathVariable(name = "userId") Integer userId, Model model) {
        EventBean eventById = EventMapper.eventToEventBean(service.getEventById(id));
        model.addAttribute("eventBean", eventById);
        UserBean userBean = UserMapper.userToUserBean(userService.getUserById(userId));
        model.addAttribute("userBean", userBean);
        service.addUserToEvent(id, userId);
        return "eventForm";

    }
    @GetMapping(value = "/delete-user-from-event/{id}/{userId}")
    public String deleteUserFromEventById(@PathVariable(name = "id") Integer id,
                                  @PathVariable(name = "userId") Integer userId, Model model) {
        service.deleteUserFromEventById(id, userId);
        List<Event> allEventsByUserId = service.getAllEventsByUserId(userId);
        allEventsByUserId.stream()
                .filter(Objects::nonNull)
                .forEach(x -> x.setCreaterUserId(userId));
        EventBean eventBean = new EventBean();
        eventBean.setUserId(userId);
        UserBean userById = UserMapper.userToUserBean(userService.getUserById(userId));
        List<Event> eventsWithUser = service.findAllEventsByUserId(userId);

        model.addAttribute("userEvents", allEventsByUserId);
        model.addAttribute("eventBean", eventBean);
        model.addAttribute("userBean", userById);
        model.addAttribute("eventsWithUser", eventsWithUser);
        return "listEvents";
    }




}
