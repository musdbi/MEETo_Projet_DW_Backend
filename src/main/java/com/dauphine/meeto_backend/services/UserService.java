package com.dauphine.meeto_backend.services;

import com.dauphine.meeto_backend.exceptions.NullObjectException;
import com.dauphine.meeto_backend.exceptions.ResourceNotFoundException;
import com.dauphine.meeto_backend.models.User;
import com.dauphine.meeto_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No user with id: " + userId + " found"));
    }

    public User createUser(User user) {
        if (user == null) {
            throw new NullObjectException("User is NULL in createUser method");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user == null) {
            throw new NullObjectException("User is NULL in updateUser method");
        }

        if (!userRepository.existsById(user.getUserId())) {
            throw new ResourceNotFoundException("No user with id: " + user.getUserId() + " found");
        }

        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("No user with id: " + userId + " found");
        }
        userRepository.deleteById(userId);
    }
    
    public User authenticateUser(String email, String password) {
    	if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
    	if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (password.equals(user.getPswd())) {
                return user;
            }
        }
        throw new ResourceNotFoundException("Invalid email or password");
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    public User findByUserId(UUID userId) {
        return userRepository.findByUserId(userId);
    }
    
}
