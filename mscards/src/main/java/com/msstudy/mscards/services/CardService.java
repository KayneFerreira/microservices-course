package com.msstudy.mscards.services;

import com.msstudy.mscards.domain.entities.CardEntity;
import com.msstudy.mscards.domain.repository.CardRepository;
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
        return repository.findByMonthlyIncomeLessThanEqual(bdIncome);
    }

}
