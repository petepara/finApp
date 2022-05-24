package com.andersen.creditservice.controller;

import com.andersen.creditservice.model.entity.Card;
import com.andersen.creditservice.model.entity.Client;
import com.andersen.creditservice.repository.CardRepository;
import com.andersen.creditservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {

    private RestTemplate restTemplate;
    private CardService cardService;

    @GetMapping("/{clientName}")
    public List<Card> getAllClientsCards(@PathVariable String clientName){
        ResponseEntity<Client> client = restTemplate.getForEntity("http://localhost:8081/user/" + clientName, Client.class);
        return cardService.findAllCards(client.getBody().getId());
    }

}
