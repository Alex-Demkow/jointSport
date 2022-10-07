package com.jointsport.jointSport.dto;

import com.jointsport.jointSport.model.StatusEvent;
import com.jointsport.jointSport.model.TypeEvent;
import com.jointsport.jointSport.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class EventBean {


    private Integer id;
    @NotNull(message = "Date should not be empty")
    @FutureOrPresent


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull(message = "Time should not be empty")
    private LocalTime time;
    @NotEmpty(message = "Address should not be empty")
    @Size(min = 2, max = 50, message = "Address should be between 2 and 50 characters")
    private String address;

    private TypeEvent type;
    private StatusEvent status;
    @Size(max = 500, message = "Description should be to 500 characters")
    private String description;
    private Integer userId;
    private List<User> eventUsers;
    @NotNull(message = "Number of users should not be empty")
    @Min(value = 1, message = "Number of users should be greater than 1")
    private Integer numberOfUsers;
    @NotEmpty(message = "Select type")
    private String typeEvent;



    public EventBean(Integer id, Date date, LocalTime time, String address, TypeEvent type, StatusEvent status, String description, List<User> eventUsers, Integer userId, Integer numberOfUsers, String typeEvent) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.address = address;
        this.type = type;
        this.status = status;
        this.description = description;
        this.eventUsers = eventUsers;
        this.userId = userId;
        this.typeEvent = typeEvent;
        this.numberOfUsers = numberOfUsers;
    }

    public List<TypeEvent> getTypeEventList() {
        return typeEventList;
    }


    private final List<TypeEvent> typeEventList = Arrays.asList(TypeEvent.OUTDOOR_WORKOUT, TypeEvent.BIKE_RIDE, TypeEvent.RUN);
    //    private Chat chat;





}
