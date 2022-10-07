package com.jointsport.jointSport.service.impl;

import com.jointsport.jointSport.dto.EventBean;
import com.jointsport.jointSport.dto.EventSearchInfo;
import com.jointsport.jointSport.dto.UserBean;
import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.mapper.EventMapper;
import com.jointsport.jointSport.model.User;
import com.jointsport.jointSport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jointsport.jointSport.repository.EventRepository;
import com.jointsport.jointSport.service.EventService;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Event createEvent(EventBean eventBean) {
        User fistUserToEvent = userRepository.findById(eventBean.getUserId())
                .orElse(null);
        Event event = EventMapper.eventBeanToEvent(eventBean);
        if (Objects.nonNull(fistUserToEvent)) {
            event.setEventUsers(Arrays.asList(fistUserToEvent));
        }
        return repository.save(event);
    }

    @Override
    public Event updateEvent(EventBean eventBean) {
        return repository.save(EventMapper.eventBeanToEvent(eventBean));
    }

    @Override
    public void deleteEventById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Event> getAllEvents(EventSearchInfo search) {
        boolean isNormalObject = false;
        List<Event> searchListEvents = new ArrayList<>();
        List<Event> eventList = repository.findAll();
  //      User user = userRepository.getById();

        for (Event event : eventList) {
            if (search.getType() == null || event.getType().equals(search.getType())) {
                if (search.getUserName() == "" || userRepository.getById(event.getCreaterUserId()).getName()
                                                                .equals(search.getUserName())) {
//                if (event.getEventUsers().stream()
//                        .filter(Objects::nonNull)
//                        .anyMatch(x -> x.getName()
//                                .equals(search.getUserName()) || x.getLastName()
//                                .equals(search.getUserLastName()))
//                ) {
//                    isNormalObject = true;
//                }


                    if (search.getMinNumberOfUsers() == null || event.getNumberOfUsers() >= search.getMinNumberOfUsers()) {
                        if (search.getMaxNumberOfUsers() == null || event.getNumberOfUsers() <= search.getMaxNumberOfUsers()) {
                            isNormalObject = true;
                        }
                    }
                }
            }

//                if(search.getStartDate() == null || !event.getDate().before(search.getStartDate())){//нужно отнять 1 минуту от даты поиска
//                    isNormalObjetc = true;
//                }
//                if(search.getEndDate() == null || !event.getDate().after(search.getEndDate())){//нужно добавить 1 минуту от даты поиска
//                    isNormalObjetc = true;
//                }
//            if (search.getAddress() == null || event.getAddress().equals(search.getAddress()) && !isNormalObjetc) {
//                isNormalObjetc = true;
//            }
//           Integer userId =  event.getCreaterUserId();
//            List<User> eventUsers = event.getEventUsers();
//            for (User user: event.getEventUsers()) {
//                if (user.getId() == event.getCreaterUserId()) {
//                  String userName = user.getName();
//                  String userLastName = user.getLastName();
//                }
//            }
//            ;
//                if (search.getUserName() == null || userRepository.getById(event.getCreaterUserId()).getName().equals(search.getUserName())) {// у getEventUsers нужно взять UserName
//                    isNormalObject = true;
//            }
//            if (search.getUserLastName() == null || event.getEventUsers().equals(search.getUserLastName())) {// у getEventUsers нужно взять UserLastName
//                isNormalObjetc = true;
//            }

//            if (search.getUserName() == null || search.getUserLastName() == null) {
//                isNormalObject = true;
//            }

            if (isNormalObject) {
                searchListEvents.add(event);
                isNormalObject = false;
            }
        }


        return searchListEvents;
    }

    @Override
    public Event getEventById(Integer id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Event> getEventsByTypeAndDate(TypeEvent type, Data data) {
        return null;
    }

    @Override
    public List<Event> getAllEventsByUserId(Integer userId) {

        return repository.findEventByCreaterUserId(userId);
    }

    @Override
    public void addUserToEvent(Integer id, Integer userId) {
        User nextUserToEvent = userRepository.getById(userId);
        Event event =  getEventById(id);
        boolean isNext = true;
        if (Objects.nonNull(nextUserToEvent)) {
            List<User> users = event.getEventUsers();
            for (User user: users) {
                if (user.getId() == userId){
                    isNext = false;
                }
            }
            if (isNext == true) {
                users.add(nextUserToEvent);
                event.setEventUsers(users);
                repository.save(event);
            }
        }
    }

    @Override
    public List<User> getEventUsers(Integer id) {
        return repository.findAllEventUsersById(id);
    }

    @Override
    public List<Event> findAllEventsByUserId(Integer userId) {
        List<Event> allEvents = repository.findAll();
        List<Event> eventsWithUser = new ArrayList<>();
        for (Event event : allEvents) {
            List<User> eventUsers = event.getEventUsers();
            for (User user : eventUsers) {
                if (user.getId() == userId && event.getCreaterUserId() != userId) {
                    eventsWithUser.add(event);
                }
            }
        }
        return eventsWithUser;
    }

    @Override
    public void deleteUserFromEventById(Integer id, Integer userId) {
        Event event =  getEventById(id);
        List<User> users = event.getEventUsers();
        for (User user: users) {
            if (user.getId() == userId){
                users.remove(user);
                event.setEventUsers(users);
                repository.save(event);
            }
        }

    }
}
