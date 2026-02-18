package com.msstudy.ms_cards.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msstudy.ms_cards.domain.entities.CardEntity;
import com.msstudy.ms_cards.domain.entities.ClientCard;
import com.msstudy.ms_cards.dtos.CardSaveRequest;
import com.msstudy.ms_cards.dtos.ClientCardResponse;
import com.msstudy.ms_cards.services.CardService;
import com.msstudy.ms_cards.services.ClientCardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final ClientCardService clientCardService;

    @GetMapping("/test")
    public String status() {
        return "Cards endpoint ok";
    }

    @PostMapping
    public ResponseEntity<CardEntity> createNewCard(@RequestBody CardSaveRequest request) {
        CardEntity response = cardService.saveNewCard(request.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CardEntity>> getCardByMinimumIncome(@RequestParam("income") Long income) {
        List<CardEntity> list = cardService.getCardsMinimalIncome(income);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<ClientCardResponse>> getCardByClientCpf(@RequestParam("cpf") String cpf) {
        List<ClientCard> list = clientCardService.findCardsByCpf(cpf);
        List<ClientCardResponse> mappedList = list.stream()
                .map(ClientCardResponse::fromModel)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(mappedList);
    }

}
