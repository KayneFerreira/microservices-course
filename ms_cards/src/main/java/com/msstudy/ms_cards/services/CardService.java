package com.msstudy.ms_cards.services;

import com.msstudy.ms_cards.domain.entities.CardEntity;
import com.msstudy.ms_cards.infra.repository.CardRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CardEntity saveNewCard(CardEntity card) {
        return repository.save(card);
    }

    public List<CardEntity> getCardsMinimalIncome(Long income) {
        BigDecimal bdIncome = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(bdIncome);
    }

}
