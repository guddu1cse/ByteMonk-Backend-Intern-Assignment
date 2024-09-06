package com.backend.backendApp.Controller;

import com.backend.backendApp.Entity.User;
import com.backend.backendApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

//base URL:   http://localhost:7070/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("longed in Successfully" , HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){
        return new ResponseEntity<>( userService.getUsers(), HttpStatus.OK);
    }


    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        return new ResponseEntity<>(userService.getByUserName(userName) , HttpStatus.OK);
    }

    @GetMapping("/getDetails")
    public ResponseEntity<User> getDetails(Authentication authentication){
        return new ResponseEntity<>(userService.getCurrentUser(authentication) , HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
//    @GetMapping("/normal_user")
//    public ResponseEntity<String> normalUser(){
//        return new ResponseEntity<>("i am normal user !" , HttpStatus.OK);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/user_admin")
//    public ResponseEntity<String> adminUser(){
//        return new ResponseEntity<>("i am admin user !" , HttpStatus.OK);
//    }
}
