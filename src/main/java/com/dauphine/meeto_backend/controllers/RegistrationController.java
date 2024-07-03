package com.dauphine.meeto_backend.controllers;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.registration.*;
import com.dauphine.meeto_backend.models.User;
import com.dauphine.meeto_backend.models.Event;
import com.dauphine.meeto_backend.services.RegistrationService;
import com.dauphine.meeto_backend.services.UserService;

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
@RequestMapping("/registrations")
@Validated
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(
            summary = "Get all registrations",
            description = "Retrieve a list of all registrations"
    )
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get registration by ID",
            description = "Retrieve a registration using its ID"
    )
    public List<Registration> getRegistrationsByUserId(@PathVariable UUID userId) {
        User user = userService.findByUserId(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return registrationService.findByUser(user);
    }

    @PostMapping
    @Operation(
            summary = "Add a new registration",
            description = "Create a new registration"
    )
    public ResponseEntity<Registration> addRegistration(
            @Valid @RequestBody Registration registration) {
        try {
            Registration newRegistration = registrationService.createRegistration(registration);
            return ResponseEntity.status(HttpStatus.CREATED).body(newRegistration);
        } catch (NullObjectException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a registration",
            description = "Remove a registration by its ID"
    )
    public ResponseEntity<Void> deleteRegistration(
            @Parameter(description = "ID of the registration to delete")
            @PathVariable("id") RegistrationId id) {
        try {
            registrationService.deleteRegistration(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get registrations by user ID",
            description = "Retrieve all registrations for a specified user"
    )
    public List<Registration> findByUser(
            @Parameter(description = "ID of the user")
            @PathVariable("userId") User user) {
        return registrationService.findByUser(user);
    }

    @GetMapping("/event/{eventId}")
    @Operation(
            summary = "Get registrations by event ID",
            description = "Retrieve all registrations for a specified event"
    )
    public List<Registration> findByEvent(
            @Parameter(description = "ID of the event")
            @PathVariable("eventId") Event event) {
        return registrationService.findByEvent(event);
    }

    @GetMapping("/exists")
    @Operation(
            summary = "Check if a registration exists",
            description = "Check if a registration exists for a specified user and event"
    )
    public ResponseEntity<Boolean> existsByUserAndEvent(
            @Parameter(description = "User details")
            @RequestBody User user,
            @Parameter(description = "Event details")
            @RequestBody Event event) {
        boolean exists = registrationService.existsByUserAndEvent(user, event);
        return ResponseEntity.ok(exists);
    }
}