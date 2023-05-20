package com.parcial.parcial.repository;
import com.parcial.parcial.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmail(String email);
}
