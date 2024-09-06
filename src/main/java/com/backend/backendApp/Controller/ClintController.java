package com.backend.backendApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clint")
public class ClintController {

    @GetMapping
    public ResponseEntity<String> clint(){
        return new ResponseEntity<>("i am clint !" , HttpStatus.OK);
    }
}
