package com.andersen.creditservice.service;

import com.andersen.creditservice.model.entity.Card;
import com.andersen.creditservice.model.entity.Client;

import java.util.List;
import java.util.UUID;

public interface CardService {
    List<Card> findAllCards(UUID clientId);
}



