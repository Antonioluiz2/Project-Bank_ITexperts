package com.itexperts.java.projeto.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itexperts.java.projeto.model.Account;
import com.itexperts.java.projeto.model.Card;
import com.itexperts.java.projeto.repository.AccountRepository;
import com.itexperts.java.projeto.repository.CardRepository;
import com.itexperts.java.projeto.repository.CardTypeRepository;

@Service
public class AccountSservice {
	//TODO exceptions são lançadas aqui com try catch

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardTypeRepository cardTypeRepository;

    @Transactional
    public Account create(Account account) {
        persistCardEntity(account);
        return accountRepository.save(account);
    }

    //TODO exception se nao tiver nenhum card sendo passado
    //TODO exception se tiver um card igual ja cadastrado
    //TODO problema com DTO Response: construtor Account recebendo Card e tipo card
    //TODO ver problema de associação bidirecional de retornar nulo
    private void persistCardEntity(Account account) {
        List<Card> cards = account.getCards();
        for (Card card : cards) {
            //TODO forma de pegar os card types existentes no bnco para salvar
//            String flag = card.getFlag().name();
//            String name = card.getCardType().getName().name();
//            Optional<CardType> cardType = cardTypeRepository.findByName(name);
//            card.setCardType(cardType.get());
            card.setAccount(account);
            cardRepository.save(card);
        }
    }
}
