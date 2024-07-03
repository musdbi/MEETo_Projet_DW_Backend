package com.dauphine.meeto_backend.services;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.feedback.Feedback;
import com.dauphine.meeto_backend.models.feedback.FeedbackId;
import com.dauphine.meeto_backend.repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(FeedbackId feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("No feedback with id: " + feedbackId + " found"));
    }

    public Feedback createFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new NullObjectException("Feedback is NULL in createFeedback method");
        }
        return feedbackRepository.save(feedback);
    }


    public void deleteFeedback(FeedbackId feedbackId) {
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new ResourceNotFoundException("No feedback with id: " + feedbackId + " found");
        }
        feedbackRepository.deleteById(feedbackId);
    }

    public List<Feedback> findByEvent(UUID eventId) {
        return feedbackRepository.findByEventEventId(eventId);
    }

    public List<Feedback> findByUser(UUID userId) {
        return feedbackRepository.findByUserUserId(userId);
    }
}