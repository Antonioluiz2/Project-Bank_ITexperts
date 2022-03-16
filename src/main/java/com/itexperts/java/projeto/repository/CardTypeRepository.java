package com.itexperts.java.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itexperts.java.projeto.model.CardType;

public interface CardTypeRepository extends JpaRepository<CardType, Integer>{
	Optional<CardType>findByName(String Name);
}
