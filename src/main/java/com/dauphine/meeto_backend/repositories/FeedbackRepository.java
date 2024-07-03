package com.dauphine.meeto_backend.repositories;

import com.dauphine.meeto_backend.models.feedback.Feedback;
import com.dauphine.meeto_backend.models.feedback.FeedbackId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, FeedbackId> {
	
	List<Feedback> findByEventEventId(UUID eventId);   
	
	List<Feedback> findByUserUserId(UUID userId);
}