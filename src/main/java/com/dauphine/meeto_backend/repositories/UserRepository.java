package com.dauphine.meeto_backend.repositories;

import com.dauphine.meeto_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	
    Optional<User> findByEmail(String email);
    
    Optional<User> findByUserName(String userName);
    
    User findByUserId(UUID userId);
    
    boolean existsByEmail(String email);
    
    boolean existsByUserName(String userName);
    
}
