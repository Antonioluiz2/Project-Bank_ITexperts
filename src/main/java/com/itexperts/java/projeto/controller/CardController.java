package com.itexperts.java.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.itexperts.java.projeto.model.Card;
import com.itexperts.java.projeto.service.CardService;

public class CardController {
	@Autowired
    private CardService cardService;

    @PostMapping("/{account_id}")
    public ResponseEntity<Card> create(@PathVariable Integer account_id, @RequestBody Card card) {
        Card cardReturned = cardService.create(account_id, card);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardReturned);
    }

    //TODO  list all cards from account

    //TODO get one card by id

    //TODO list all card from all acounts

    //TODO list all cards from specific type (@RequestParam "name"="CREDIT_CARD") -> COM INDEX BUSCA DISSO FICA OTIMIZADA!

    //TODO delete one card from account

    //TODO delete all card from account
}
