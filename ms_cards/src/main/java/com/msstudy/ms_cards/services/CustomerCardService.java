package com.msstudy.ms_cards.services;

import com.msstudy.ms_cards.domain.entities.CustomerCard;
import com.msstudy.ms_cards.infra.repository.CustomerCardRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerCardService {

    private final CustomerCardRepository repository;

    public CustomerCardService(CustomerCardRepository repository) {
        this.repository = repository;
    }

    public List<CustomerCard> findCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
