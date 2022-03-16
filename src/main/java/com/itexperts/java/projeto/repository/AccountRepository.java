package com.itexperts.java.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itexperts.java.projeto.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
