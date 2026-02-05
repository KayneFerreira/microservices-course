package com.msstudy.mscards.controller;

import com.msstudy.mscards.domain.entities.CardEntity;
import com.msstudy.mscards.dtos.CardSaveRequest;
import com.msstudy.mscards.services.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cards")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String status() {
        return "Cards endpoint ok";
    }

    @PostMapping
    public ResponseEntity<CardEntity> createNewCard(@RequestBody CardSaveRequest request) {
        CardEntity response = service.saveNewCard(request.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
