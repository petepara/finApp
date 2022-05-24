package com.andersen.creditservice.model.entity;

import com.andersen.creditservice.model.constant.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "co_brand")
    private String coBrand;

    @Column(name = "in_digital_wallet")
    private boolean inDigitalWallet;
    @Column(name = "is_virtual")
    private boolean isVirtual;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;



}
