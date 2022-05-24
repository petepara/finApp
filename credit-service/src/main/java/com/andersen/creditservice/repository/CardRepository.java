package com.andersen.creditservice.repository;

import com.andersen.creditservice.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository <Card, Long> {

    List<Card> findAllByClientId(UUID clientId);
}
