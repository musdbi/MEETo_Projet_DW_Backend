package com.dauphine.meeto_backend.repositories;

import com.dauphine.meeto_backend.models.registration.*;
import com.dauphine.meeto_backend.models.User;
import com.dauphine.meeto_backend.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
	
    List<Registration> findByUser(User user);
    
    List<Registration> findByEvent(Event event);
    
    boolean existsByUserAndEvent(User user, Event event);
    
}