package com.dauphine.meeto_backend.dto;

import java.util.UUID;

public class FeedbackDTO {

    private UUID feedbackId;
    private UserDTO user;
    private EventDTO event;
    private int rating;

    public FeedbackDTO(UUID feedbackId, UserDTO user, EventDTO event, int rating) {
        this.feedbackId = feedbackId;
        this.user = user;
        this.event = event;
        this.rating = rating;
    }

    public FeedbackDTO() {}

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = feedbackId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public EventDTO getEvent() {
        return event;
    }

    public void setEvent(EventDTO event) {
        this.event = event;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
