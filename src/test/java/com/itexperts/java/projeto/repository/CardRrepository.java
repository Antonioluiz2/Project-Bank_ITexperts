package com.itexperts.java.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itexperts.java.projeto.model.Card;

@Repository
public interface CardRrepository extends JpaRepository<Card, Integer> {

}
