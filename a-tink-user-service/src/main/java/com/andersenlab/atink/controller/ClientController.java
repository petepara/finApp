package com.andersenlab.atink.controller;

import com.andersenlab.atink.service.ClientService;
import com.andersenlab.atink.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class ClientController {

    private ClientService clientService;

    @GetMapping("/{firstName}")
    public ResponseEntity<?> getSomething(@PathVariable String firstName){

        return new ResponseEntity<>(clientService.findByFirstName(firstName), HttpStatus.OK);
    }



}
