package com.backend.backendApp.ExceptionHandler;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String getMessage(){
        System.out.println("Custom Exception Message : you have exception in you code !");
        return "Custom Exception Message : you have exception in you code !";
    }

}
