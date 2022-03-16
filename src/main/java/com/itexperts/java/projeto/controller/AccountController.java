package com.itexperts.java.projeto.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itexperts.java.projeto.dto.account.AccountRequestDTO;
import com.itexperts.java.projeto.dto.account.AccountResponseDTO;
import com.itexperts.java.projeto.service.AccountService;

import javax.websocket.server.PathParam;
import java.util.List;
//import java.util.List;


@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
	 @Autowired
	    private AccountService accountService;

	    //C- Create
	    @ApiOperation(value = "Create an account")
	    @PostMapping
	    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO requestDTO) {
	        AccountResponseDTO accountSaved = accountService.create(requestDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(accountSaved);
	    }


	    //R - Read All
	    @ApiOperation(value = "List all accounts")
	    @GetMapping("/all")
	    public ResponseEntity<List<AccountResponseDTO>> getAll() {
	        List<AccountResponseDTO> accountsReturned = accountService.getAll();
	        return ResponseEntity.ok().body(accountsReturned);
	    }

	    //R - Read All Pageable
	    @ApiOperation(value = "List all accounts but pageable")
	    @GetMapping("/all/pageable")
	    public ResponseEntity<Page<AccountResponseDTO>> getAllPageable(Pageable pageable) {
	        Page<AccountResponseDTO> accountsReturned = accountService.getAllPageable(pageable);
	        return ResponseEntity.ok().body(accountsReturned);
	    }

	    //R - Read one by id
	    @ApiOperation(value = "Get one account by id")
	    @GetMapping("/{id}")
	    public ResponseEntity<AccountResponseDTO> getById(@PathVariable Integer id) {
	        return ResponseEntity.ok().body(accountService.getById(id));
	    }

	    //R - Read One by Register Id
	    @ApiOperation(value = "Get one account by register id")
	    @GetMapping
	    public ResponseEntity<AccountResponseDTO> getByRegisterId(@PathParam("registerId") String registerId) {
	        return ResponseEntity.ok().body(accountService.getByRegisterId(registerId));
	    }

	    //U - Update By Id
	    @ApiOperation(value = "Update one account by id")
	    @PutMapping("/{id}")
	    public ResponseEntity<AccountResponseDTO> updateById(@PathVariable Integer id, @RequestBody AccountRequestDTO requestDTO) {
	        AccountResponseDTO accountUpdated = accountService.updateById(id, requestDTO);
	        return ResponseEntity.ok().body(accountUpdated);
	    }

	    //U - Update By Register Id
	    @ApiOperation(value = "Update one account by register id")
	    @PutMapping
	    public ResponseEntity<AccountResponseDTO> updateByRegisterId(@PathParam("registerId") String registerId, @RequestBody AccountRequestDTO requestDTO) {
	        AccountResponseDTO accountUpdated = accountService.updateByRegisterId(registerId, requestDTO);
	        return ResponseEntity.ok().body(accountUpdated);
	    }

	    //D - Delete one by id
	    //TODO indicar que nao pode deletar conta quando tiver cartao associado - Controller
	    @ApiOperation(value = "Delete one account by id")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteOneAccountById(@PathVariable Integer id) {
	        accountService.deleteById(id);
	        return ResponseEntity.ok().build();
	    }

	    //D - Delete one by register id
	    //TODO indicar que nao pode deletar conta quando tiver cartao associado - Controller
	    @ApiOperation(value = "Delete one account by register id")
	    @DeleteMapping
	    public ResponseEntity<Void> deleteOneAccountByRegisterId(@PathParam("registerId") String registerId) {
	        accountService.deleteByRegisterId(registerId);
	        return ResponseEntity.ok().build();
	    }


}
