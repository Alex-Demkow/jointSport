package com.jointsport.jointSport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;


    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "address")
    private String address;

    @Column(name = "type_event")
    @Enumerated(EnumType.STRING)
    private TypeEvent type;

    @Column(name = "status_event")
    private StatusEvent status;

    @Column(name = "description_event")
    private String description;

    @ManyToMany
//            (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//            (mappedBy = "events")
//    @JoinTable(name= "event_user",
//            joinColumns = {@JoinColumn(name="event_id")},
//            inverseJoinColumns = {@JoinColumn(name="user_id")})
   //         (fetch = FetchType.LAZY, mappedBy = "event", cascade = CascadeType.ALL)
    @Column(name = "event_users")
    private List<User> eventUsers;

    @Column(name = "number_of_users")
    private Integer numberOfUsers;

    @Column(name = "user_id")
    private Integer createrUserId;

    public Integer getCreaterUserId() {
        return createrUserId;
    }

    public void setCreaterUserId(Integer createrUserId) {
        this.createrUserId = createrUserId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TypeEvent getType() {
        return type;
    }

    public void setType(TypeEvent type) {
        this.type = type;
    }

    public StatusEvent getStatus() {
        return status;
    }

    public void setStatus(StatusEvent status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<User> eventUsers) {
        this.eventUsers = eventUsers;
    }

    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Integer numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
