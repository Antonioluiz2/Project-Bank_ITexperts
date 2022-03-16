package com.itexperts.java.projeto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.repository.CardTypeRepository;

@Service
public class CardTypeSservice {
	@Autowired
    private CardTypeRepository cardTypeRepository;

    @Transactional
    public CardType create(CardType cardType) {
        return cardTypeRepository.save(cardType);
    }

    @Transactional
    public List<CardType> getAll() {
        return cardTypeRepository.findAll();
    }
}
