package com.jointsport.jointSport.repository;

import com.jointsport.jointSport.model.Event;
import com.jointsport.jointSport.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventByCreaterUserId(Integer createrUserId);

    List<User> findAllEventUsersById(Integer id);
}
