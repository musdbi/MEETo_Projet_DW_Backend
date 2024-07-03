package com.dauphine.meeto_backend.models.feedback;

import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.models.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(FeedbackId.class)
@Table(name = "feedback")
public class Feedback implements Serializable {

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

    @Column(name = "rating")
    @Min(value = 1, message = "Rating should not be less than 1")
    @Max(value = 5, message = "Rating should not be more than 5")
    private int rating;

    public Feedback() {}

    public Feedback(User user, Event event, int rating) {
        this.user = user;
        this.event = event;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Feedback that = (Feedback) obj;
        return Objects.equals(user, that.user) && Objects.equals(event, that.event);
    }
}