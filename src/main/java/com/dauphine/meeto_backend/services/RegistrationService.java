package com.dauphine.meeto_backend.services;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.registration.*;
import com.dauphine.meeto_backend.models.User;
import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.repositories.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(RegistrationId id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No registration with id: " + id + " found"));
    }

    public Registration createRegistration(Registration registration) {
        if (registration == null) {
            throw new NullObjectException("Registration is NULL in createRegistration method");
        }
        return registrationRepository.save(registration);
    }

    public void deleteRegistration(RegistrationId id) {
        if (!registrationRepository.existsById(id)) {
            throw new ResourceNotFoundException("No registration with id: " + id + " found");
        }
        registrationRepository.deleteById(id);
    }
    
    public List<Event> getEventsByUser(User user) {
        return registrationRepository.findByUser(user).stream()
                .map(Registration::getEvent)
                .collect(Collectors.toList());
    }

    public List<Registration> findByUser(User user) {
        return registrationRepository.findByUser(user);
    }

    public List<Registration> findByEvent(Event event) {
        return registrationRepository.findByEvent(event);
    }

    public boolean existsByUserAndEvent(User user, Event event) {
        return registrationRepository.existsByUserAndEvent(user, event);
    }
}