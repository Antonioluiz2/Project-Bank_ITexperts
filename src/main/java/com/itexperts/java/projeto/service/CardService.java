package com.itexperts.java.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itexperts.java.projeto.model.Account;
import com.itexperts.java.projeto.model.Card;

import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.repository.AccountRepository;
import com.itexperts.java.projeto.repository.CardRepository;
import com.itexperts.java.projeto.repository.CardTypeRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {
	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CardTypeRepository cardTypeRepository;

	@Transactional
	public Card create(Integer accountId, Card card) {

		// verificar se account existe
		Optional<Account> accountReturned = accountRepository.findById(accountId);
		accountReturned.orElseThrow(() -> new RuntimeException("Account not found."));

		// verificar se o tupo de cartão existe
		Optional<CardType> cardTypeReturned = cardTypeRepository.findByName(card.getCardType().getName());
		cardTypeReturned.orElseThrow(() -> new RuntimeException("Card type not found."));

		// inserindo relação de conta no cartão
		card.setAccount(accountReturned.get());
		// inserindo relação de tipo cartão no cartão
		card.setCardType(cardTypeReturned.get());
		// inserindo cartão na conta
		accountReturned.get().getCards().add(card);

		// atualizando objeto de conta com o novo cartão inserido na lista
		accountRepository.save(accountReturned.get());
		// salvando cartão na base de cartões
		Card cardPersisted = cardRepository.save(card);

		return cardPersisted;
	}

}
