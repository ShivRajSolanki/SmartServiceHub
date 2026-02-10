package com.smarServiceHub.user_Service.Repository;

import com.smarServiceHub.user_Service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
