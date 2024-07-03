package com.dauphine.meeto_backend.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EventDTO {

    private UUID eventId;
    private String name;
    private String address;
    private String city;
    private String pincode;
    private String description;
    private Date date;
    private CategoryDTO category;
    private UserDTO organizer;
    private List<UserDTO> participants;

    public EventDTO(UUID eventId, String name, String address, String city, String pincode, String description, Date date, CategoryDTO category, UserDTO organizer, List<UserDTO> participants) {
        this.eventId = eventId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.description = description;
        this.date = date;
        this.category = category;
        this.organizer = organizer;
        this.participants = participants;
    }

    public EventDTO() {}

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public UserDTO getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserDTO organizer) {
        this.organizer = organizer;
    }

    public List<UserDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserDTO> participants) {
        this.participants = participants;
    }
}
