package com.backend.backendApp.Service.Impl;

import com.backend.backendApp.Entity.User;
import com.backend.backendApp.ExceptionHandler.ResourceNotFound;
import com.backend.backendApp.Repo.UserRepo;
import com.backend.backendApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
   private PasswordEncoder passwordEncoder;


    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User user) {
        String pass = user.getPassword();
        user.setPassword(passwordEncoder.encode(pass));
        return userRepo.save(user);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepo.findByUserName(userName).orElseThrow(
                ()->new ResourceNotFound("Resource not found for given username "+userName)
        );
    }

    @Override
    public User getCurrentUser(Authentication authentication) {
        return userRepo.findByUserName(authentication.getName()).orElseThrow(()-> new ResourceNotFound("Resource Not Found !"));
    }

}
