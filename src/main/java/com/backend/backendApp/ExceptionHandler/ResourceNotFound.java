package com.backend.backendApp.ExceptionHandler;


public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String message){
        super(message);
    }
}
