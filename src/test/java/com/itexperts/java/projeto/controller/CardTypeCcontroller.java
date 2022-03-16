package com.itexperts.java.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.service.CardTypeService;

@RestController
@RequestMapping("/api/v1/card_types")
public class CardTypeCcontroller {
    @Autowired
    private CardTypeService cardTypeService;

    //TODO fazer controller de CardType funcionar, pois quero ser capaz de inserir novos tipos de cartão via API
    @PostMapping
    public ResponseEntity<CardType> create(CardType cardType) {
        CardType cardTypeReturned = cardTypeService.create(cardType);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardTypeReturned);
    }

    @GetMapping
    public ResponseEntity<List<CardType>> getAll() {
        List<CardType> cardTypesReturned = cardTypeService.getAll();
        return ResponseEntity.ok().body(cardTypesReturned);
    }
}
