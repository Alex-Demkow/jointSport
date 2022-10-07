package com.jointsport.jointSport.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity//что делает эта аннотация?
@Table(name = "users")//что делает эта аннотация?
@Data
public class User {
    @Id//что делает эта аннотация?
    @GeneratedValue(strategy = GenerationType.AUTO)//что делает эта аннотация? что делает запись в скобках?
    @Column(name = "Id")//что делает эта аннотация?
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ElementCollection//добавляет коллекцию id
    @Column(name = "user_friends_ids")
    private List<Integer> friendsIds;

    @ManyToMany
//            (fetch = FetchType.EAGER, mappedBy = "eventUsers")
//            (mappedBy = "users")
//    @JoinTable(name= "user_event",
//            joinColumns = {@JoinColumn(name="user_id")},
//            inverseJoinColumns = {@JoinColumn(name="event_id")})
 //          (fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Event> events;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(List<Integer> friendsIds) {
        this.friendsIds = friendsIds;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
