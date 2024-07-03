package com.dauphine.meeto_backend.controllers;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.models.User;
import com.dauphine.meeto_backend.models.registration.Registration;
import com.dauphine.meeto_backend.services.EventService;
import com.dauphine.meeto_backend.services.RegistrationService;
import com.dauphine.meeto_backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/events")
@Validated
public class EventController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    @Operation(
            summary = "Get all events",
            description = "Retrieve a list of all events"
    )
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/users/{userId}")
    @Operation(
            summary = "Get events by user ID",
            description = "Retrieve all events for the specified user"
    )
    public List<Event> findAllByUserId(
            @Parameter(description = "ID of the user")
            @PathVariable("userId") UUID userId) {
        return eventService.findAllByOrganizerId(userId);
    }

    @PostMapping
    @Operation(
            summary = "Add a new event",
            description = "Create a new event for the specified user"
    )
    public ResponseEntity<Event> addEvent(
            @RequestBody @Valid Event event,
            @RequestHeader(value = "User-Id") UUID userId) {
        User currentUser = userService.findByUserId(userId);
        event.setOrganizer(currentUser);
        try {
            Event newEvent = eventService.addEvent(event);
            registrationService.createRegistration(new Registration(currentUser, newEvent));
            return ResponseEntity.status(HttpStatus.CREATED).body(newEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get event by ID",
            description = "Retrieve an event using its ID"
    )
    public ResponseEntity<Event> getEventById(
            @Parameter(description = "ID of the event")
            @PathVariable("id") UUID id) {
        try {
            Event event = eventService.getEventById(id);
            return ResponseEntity.ok(event);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an event",
            description = "Modify the details of an event by its ID"
    )
    public ResponseEntity<Event> updateEvent(
            @Parameter(description = "ID of the event to update")
            @PathVariable("id") UUID eventId,
            @Valid @RequestBody Event event) {
        event.setEventId(eventId);
        try {
            Event updatedEvent = eventService.updateEvent(event);
            return ResponseEntity.ok(updatedEvent);
        } catch (ResourceNotFoundException | NullObjectException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete an event",
            description = "Remove an event by its ID"
    )
    public ResponseEntity<Void> deleteEvent(
            @Parameter(description = "ID of the event to delete")
            @PathVariable("id") UUID eventId) {
        try {
            eventService.delete(eventId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/city/{city}")
    @Operation(
            summary = "Get events by city",
            description = "Retrieve all events taking place in the specified city"
    )
    public List<Event> findAllByCity(
            @Parameter(description = "City where the events take place")
            @PathVariable("city") String city) {
        return eventService.findAllByCity(city);
    }
}
