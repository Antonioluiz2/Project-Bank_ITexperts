package com.itexperts.java.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.itexperts.java.projeto.model.Account;
import com.itexperts.java.projeto.repository.AccountRepository;
import com.itexperts.java.projeto.service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountCcontroller {
	@Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account accountReturned = accountService.create(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(accountReturned);
    }

    //TODO tirar chamada direto do repository da controller e chama o service de account
    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Optional<Account> getOneAccountById(@PathVariable Integer id) {
        System.out.println("Pegando uma única conta por id");
        return accountRepository.findById(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Account> listAllAccounts() {
        System.out.println("Listando todas as contas");
        return accountRepository.findAll();
    }

    //TODO indicar que nao pdoe deletar conta quando tiver cartao associado - Controller
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteOneAccountById(@PathVariable Integer id) {
        System.out.println("Deletando uma única conta por id");
        accountRepository.deleteById(id);
    }


}
