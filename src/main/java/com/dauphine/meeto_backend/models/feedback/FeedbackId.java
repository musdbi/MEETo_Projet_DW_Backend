package com.dauphine.meeto_backend.models.feedback;

import java.io.Serializable;
import java.util.UUID;
import java.util.Objects;

public class FeedbackId implements Serializable {
	
    private UUID user;
    private UUID event;

    public FeedbackId() {}

    public FeedbackId(UUID user, UUID event) {
        this.user = user;
        this.event = event;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public UUID getEvent() {
        return event;
    }

    public void setEvent(UUID event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackId that = (FeedbackId) o;
        return user.equals(that.user) && event.equals(that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, event);
    }
}