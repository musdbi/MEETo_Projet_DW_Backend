package com.dauphine.meeto_backend.repositories;

import com.dauphine.meeto_backend.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
	
    List<Event> findByCity(String city);
    
    List<Event> findByOrganizerUserName(String Username);
    
    List<Event> findByOrganizerUserId(UUID organizerId);
    
    boolean existsByName(String name);
    
}