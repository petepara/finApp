package com.andersen.creditservice.service.impl;

import com.andersen.creditservice.model.entity.Card;
import com.andersen.creditservice.repository.CardRepository;
import com.andersen.creditservice.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;


    @Override
    public List<Card> findAllCards(UUID clientId) {
        return cardRepository.findAllByClientId(clientId);
    }
}
