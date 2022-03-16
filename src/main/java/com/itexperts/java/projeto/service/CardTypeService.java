package com.itexperts.java.projeto.service;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itexperts.java.projeto.dto.cardtype.CardTypeRequestDTO;
import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.repository.CardTypeRepository;

@Service
public class CardTypeService {
	@Autowired
	private CardTypeRepository cardTypeRepository;

	@Transactional
	public CardType create(CardTypeRequestDTO cardTypeRequestDTO) {

		// não deixa criar type repetido lançando exceção antes de mandar pro banco de
		// dados
		// banco de dados, devido ao name ser index unique, lança exception:
		// java.sql.SQLIntegrityConstraintViolationException
		Optional<CardType> cardTypeReturned = cardTypeRepository.findByName(cardTypeRequestDTO.getName());
		if (cardTypeReturned.isPresent()) {
			throw new RuntimeException("Card type already exists.");
		}

		return cardTypeRepository.save(new CardType(cardTypeRequestDTO));
	}

	@Transactional
	public List<CardType> getAll() {
		return cardTypeRepository.findAll();
	}

	// TODO método para externalizar em classe conta e card findById de card type
}
