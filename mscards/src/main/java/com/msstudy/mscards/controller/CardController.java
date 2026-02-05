package com.msstudy.mscards.controller;

import com.msstudy.mscards.domain.entities.CardEntity;
import com.msstudy.mscards.domain.entities.ClientCard;
import com.msstudy.mscards.dtos.CardSaveRequest;
import com.msstudy.mscards.dtos.ClientCardResponse;
import com.msstudy.mscards.services.CardService;
import com.msstudy.mscards.services.ClientCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
