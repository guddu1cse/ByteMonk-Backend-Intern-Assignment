package com.backend.backendApp.Controller;

import com.backend.backendApp.Entity.JwtRequest;
import com.backend.backendApp.Entity.JwtResponse;
import com.backend.backendApp.Entity.User;
import com.backend.backendApp.JwtUtils.JwtUtil;
import com.backend.backendApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//base URL:   http://localhost:7070/
@RestController
public class Auth {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    //This API authenticates a user based on the provided username and password.
    //Upon successful authentication, it generates and returns a JWT (JSON Web Token) for the user.
    //EndPoint: POST  /auth/login
    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());

            System.out.println(request.getUserName() + " login successfully !");
            return new ResponseEntity<>( new JwtResponse(jwtToken , request.getUserName() , "ACCEPTED" , ""), HttpStatus.OK);
        } catch (Exception e){

            System.out.println(request.getUserName() + " try to login with incorrect details !");
            return new ResponseEntity<>(new JwtResponse("" , "" , "REJECTED" , "Invalid UserName or Password") , HttpStatus.FORBIDDEN);
        }
    }

    //This API allows new users to register by providing their details, which are validated and stored in the system.
    //EndPoint: POST  /register
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User user){
        userService.createUser(user);
        System.out.println(user.getUsername() + " Sign Up SuccessFully");
        return new ResponseEntity<>("Sign Up Successfully !" , HttpStatus.OK);
    }


//    private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            authenticationManager.authenticate(authentication);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }


}
