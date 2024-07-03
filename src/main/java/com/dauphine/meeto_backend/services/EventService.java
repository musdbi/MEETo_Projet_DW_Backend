package com.dauphine.meeto_backend.services;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(UUID eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("No event with id: " + eventId + " found"));
    }

    public Event addEvent(Event event) {
        if (event == null) {
            throw new NullObjectException("Event is NULL in createEvent method");
        }
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        if (event == null) {
            throw new NullObjectException("Event is NULL in updateEvent method");
        }

        if (!eventRepository.existsById(event.getEventId())) {
            throw new ResourceNotFoundException("No event with id: " + event.getEventId() + " found");
        }

        return eventRepository.save(event);
    }

    public void delete(UUID eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new ResourceNotFoundException("No event with id: " + eventId + " found");
        }
        eventRepository.deleteById(eventId);
    }
    
    public List<Event> findAllByOrganizerId(UUID organizerId) {
        return eventRepository.findByOrganizerUserId(organizerId);
    }

    public List<Event> findAllByOrganizerUsername(String Username) {
        return eventRepository.findByOrganizerUserName(Username);
    }

    public List<Event> findAllByCity(String city) {
        return eventRepository.findByCity(city);
    }
}
