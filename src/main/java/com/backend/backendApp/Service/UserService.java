package com.backend.backendApp.Service;

import com.backend.backendApp.Entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    User createUser(User user);
    User getByUserName(String userName);

    User getCurrentUser(Authentication authentication);
}
