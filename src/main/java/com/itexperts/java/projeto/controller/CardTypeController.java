package com.itexperts.java.projeto.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itexperts.java.projeto.dto.cardtype.CardTypeRequestDTO;
import com.itexperts.java.projeto.dto.cardtype.CardTypeResponseDTO;
import com.itexperts.java.projeto.model.CardType;
import com.itexperts.java.projeto.service.CardTypeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/card_types")
public class CardTypeController {
	@Autowired
    private CardTypeService cardTypeService;

    //C- Create
    @ApiOperation(value = "Create a card type")
    @PostMapping
    public ResponseEntity<CardTypeResponseDTO> create(@RequestBody CardTypeRequestDTO requestDTO) {
        CardTypeResponseDTO cardTypeSaved = cardTypeService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardTypeSaved);
    }

    //R - Read All
    @ApiOperation(value = "List all card types")
    @GetMapping("/all")
    public ResponseEntity<List<CardTypeResponseDTO>> getAll() {
        List<CardTypeResponseDTO> cardTypesReturned = cardTypeService.getAll();
        return ResponseEntity.ok().body(cardTypesReturned);
    }

    //R - Read All Pageable
    @ApiOperation(value = "List all card types but pageable")
    @GetMapping("/all/pageable")
    public ResponseEntity<Page<CardTypeResponseDTO>> getAllPageable(Pageable pageable) {
        Page<CardTypeResponseDTO> cardTypesReturned = cardTypeService.getAllPageable(pageable);
        return ResponseEntity.ok().body(cardTypesReturned);
    }

    //R - Read One by Id
    @ApiOperation(value = "Get one card type by id")
    @GetMapping("/{id}")
    public ResponseEntity<CardTypeResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(cardTypeService.getById(id));
    }

    //R - Read One by Name
    @ApiOperation(value = "Get one card type by name")
    @GetMapping
    public ResponseEntity<CardTypeResponseDTO> getByName(@PathParam("name") String name) {
        return ResponseEntity.ok(cardTypeService.getByName(name));
    }

    //U - Update By Id
    @ApiOperation(value = "Update one card type by id")
    @PutMapping("/{id}")
    public ResponseEntity<CardTypeResponseDTO> updateById(@PathVariable Integer id, @RequestBody CardTypeRequestDTO requestDTO) {
        CardTypeResponseDTO cardTypeUpdated = cardTypeService.updateById(id, requestDTO);
        return ResponseEntity.ok().body(cardTypeUpdated);
    }

    //U - Update By Name
    @ApiOperation(value = "Update one card type by name")
    @PutMapping
    public ResponseEntity<CardTypeResponseDTO> updateByName(@PathParam("name") String name, @RequestBody CardTypeRequestDTO requestDTO) {
        CardTypeResponseDTO cardTypeUpdated = cardTypeService.updateByName(name, requestDTO);
        return ResponseEntity.ok().body(cardTypeUpdated);
    }

    //D - Delete One By Id
    @ApiOperation(value = "Delete one card type by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        cardTypeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //D - Delete One by Name
    @ApiOperation(value = "Delete one card type by name")
    @DeleteMapping
    public ResponseEntity<Void> deleteByName(@PathParam("name") String name) {
        cardTypeService.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
