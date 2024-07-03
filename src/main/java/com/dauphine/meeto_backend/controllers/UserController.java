package com.dauphine.meeto_backend.controllers;

import com.dauphine.meeto_backend.dto.UserDTO;
import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.User;
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
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(
            summary = "Get all users",
            description = "Retrieve a list of all users"
    )
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get user by ID",
            description = "Retrieve a user using their ID"
    )
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ID of the user")
            @PathVariable("id") UUID userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    @Operation(
            summary = "Add a new user",
            description = "Create a new user"
    )
    public ResponseEntity<User> addUser(
            @Valid @RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (NullObjectException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestBody UserDTO userLoginDto) {
        try {
            User authenticatedUser = userService.authenticateUser(userLoginDto.getEmail(), userLoginDto.getPassword());
            return ResponseEntity.ok(authenticatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update a user",
            description = "Modify the details of a user by their ID"
    )
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID of the user to update")
            @PathVariable("id") UUID userId,
            @Valid @RequestBody User user) {
        user.setUserId(userId);
        try {
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException | NullObjectException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a user",
            description = "Remove a user by their ID"
    )
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete")
            @PathVariable("id") UUID userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    @Operation(
            summary = "Get user by email",
            description = "Retrieve a user using their email"
    )
    public ResponseEntity<User> findByEmail(
            @Parameter(description = "Email of the user")
            @PathVariable("email") String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    @Operation(
            summary = "Get user by username",
            description = "Retrieve a user using their username"
    )
    public ResponseEntity<User> findByUserName(
            @Parameter(description = "Username of the user")
            @PathVariable("username") String username) {
        return userService.findByUserName(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
