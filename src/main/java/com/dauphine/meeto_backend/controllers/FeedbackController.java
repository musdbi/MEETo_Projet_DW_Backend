package com.dauphine.meeto_backend.controllers;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.feedback.Feedback;
import com.dauphine.meeto_backend.models.feedback.FeedbackId;
import com.dauphine.meeto_backend.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/feedbacks")
@Validated
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    @Operation(
            summary = "Get all feedbacks",
            description = "Retrieve a list of all feedbacks"
    )
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{userId}/{eventId}")
    @Operation(
            summary = "Get feedback by ID",
            description = "Retrieve a feedback using user ID and event ID"
    )
    public ResponseEntity<Feedback> getFeedbackById(
            @Parameter(description = "ID of the user")
            @PathVariable("userId") UUID userId,
            @Parameter(description = "ID of the event")
            @PathVariable("eventId") UUID eventId) {
        FeedbackId feedbackId = new FeedbackId(userId, eventId);
        try {
            Feedback feedback = feedbackService.getFeedbackById(feedbackId);
            return ResponseEntity.ok(feedback);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Add a new feedback",
            description = "Create a new feedback"
    )
    public ResponseEntity<Feedback> addFeedback(
            @Valid @RequestBody Feedback feedback) {
    	try {
            Feedback newFeedback = feedbackService.createFeedback(feedback);
            return ResponseEntity.status(HttpStatus.CREATED).body(newFeedback);
        } catch (NullObjectException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{userId}/{eventId}")
    @Operation(
            summary = "Delete a feedback",
            description = "Remove a feedback by user ID and event ID"
    )
    public ResponseEntity<Void> deleteFeedback(
            @Parameter(description = "ID of the user")
            @PathVariable("userId") UUID userId,
            @Parameter(description = "ID of the event")
            @PathVariable("eventId") UUID eventId) {
        FeedbackId feedbackId = new FeedbackId(userId, eventId);
        try {
            feedbackService.deleteFeedback(feedbackId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/event/{eventId}")
    @Operation(
            summary = "Get feedbacks by event ID",
            description = "Retrieve all feedbacks for a specified event"
    )
    public List<Feedback> findByEvent(
            @Parameter(description = "ID of the event")
            @PathVariable("eventId") UUID eventId) {
        return feedbackService.findByEvent(eventId);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get feedbacks by user ID",
            description = "Retrieve all feedbacks for a specified user"
    )
    public List<Feedback> findByUser(
            @Parameter(description = "ID of the user")
            @PathVariable("userId") UUID userId) {
        return feedbackService.findByUser(userId);
    }
}