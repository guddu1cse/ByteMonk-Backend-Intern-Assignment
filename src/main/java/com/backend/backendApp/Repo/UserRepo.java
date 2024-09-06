package com.backend.backendApp.Repo;

import com.backend.backendApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    Optional<User> findByUserName(String id);
}
