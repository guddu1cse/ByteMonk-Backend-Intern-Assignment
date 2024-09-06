package com.backend.backendApp.Service;

import com.backend.backendApp.Entity.User;
import com.backend.backendApp.ExceptionHandler.ResourceNotFound;
import com.backend.backendApp.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username).orElseThrow(
                ()->new ResourceNotFound("Resource not found for given username " + username)
        );

        return user;
    }
}
