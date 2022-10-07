package com.jointsport.jointSport.service;

import com.jointsport.jointSport.dto.EventBean;
import com.jointsport.jointSport.dto.EventSearchInfo;
import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.model.User;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public interface EventService {
    Event createEvent(EventBean eventBean);

    Event updateEvent(EventBean eventBean);

    void deleteEventById(Integer id);

    List<Event> getAllEvents(EventSearchInfo search);

    Event getEventById(Integer id);

    List<Event> getEventsByTypeAndDate(TypeEvent type, Data data);

    List<Event> getAllEventsByUserId(Integer userId);

    void addUserToEvent(Integer id, Integer userId);

    List<User> getEventUsers(Integer id);

    List<Event> findAllEventsByUserId(Integer userId);

    void deleteUserFromEventById(Integer id, Integer userId);
}
