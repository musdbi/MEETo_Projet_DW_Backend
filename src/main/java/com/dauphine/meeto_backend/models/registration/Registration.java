package com.dauphine.meeto_backend.models.registration;

import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@IdClass(RegistrationId.class)
@Table(name = "registration")
public class Registration implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User is required")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "event_id")
    @NotNull(message = "Event is required")
    private Event event;

    public Registration() {}

    public Registration(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}