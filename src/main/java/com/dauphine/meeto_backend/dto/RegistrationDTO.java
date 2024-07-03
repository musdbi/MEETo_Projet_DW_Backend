package com.dauphine.meeto_backend.dto;

import java.util.UUID;

public class RegistrationDTO {

    private UUID registrationId;
    private EventDTO event;
    private UserDTO user;

    public RegistrationDTO(UUID registrationId, EventDTO event, UserDTO user) {
        this.registrationId = registrationId;
        this.event = event;
        this.user = user;
    }

    public RegistrationDTO() {}

    public UUID getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(UUID registrationId) {
        this.registrationId = registrationId;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
