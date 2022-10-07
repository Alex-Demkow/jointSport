package com.jointsport.jointSport.mapper;

import com.jointsport.jointSport.dto.EventBean;
import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.TypeEvent;

public class EventMapper {


    public static EventBean eventToEventBean(Event event){
        EventBean eventBean = new EventBean();
        eventBean.setId(event.getId());
        eventBean.setDate(event.getDate());
        eventBean.setTime(event.getTime());
        eventBean.setAddress(event.getAddress());
        eventBean.setType(TypeEvent.valueOf(event.getType().name()));//?????????????
        eventBean.setStatus(event.getStatus());
        eventBean.setDescription(event.getDescription());
        eventBean.setNumberOfUsers(event.getNumberOfUsers());
        eventBean.setEventUsers(event.getEventUsers());
        eventBean.setTypeEvent(event.getType().name());
        eventBean.setUserId(event.getCreaterUserId());
        return eventBean;
    }

    public static Event eventBeanToEvent(EventBean eventBean){
        Event event = new Event();
        event.setId(eventBean.getId());
        event.setCreaterUserId(eventBean.getUserId());
        event.setDate(eventBean.getDate());
        event.setTime(eventBean.getTime());
        event.setAddress(eventBean.getAddress());
        event.setType(TypeEvent.valueOf(eventBean.getTypeEvent()));;
        event.setStatus(eventBean.getStatus());
        event.setDescription(eventBean.getDescription());
        event.setNumberOfUsers(eventBean.getNumberOfUsers());
        event.setEventUsers(eventBean.getEventUsers());

        return event;
    }



}
