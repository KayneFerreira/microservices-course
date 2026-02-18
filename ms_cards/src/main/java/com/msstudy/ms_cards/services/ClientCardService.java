package com.msstudy.ms_cards.services;

import com.msstudy.ms_cards.domain.entities.ClientCard;
import com.msstudy.ms_cards.domain.repository.ClientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCardService {

    private final ClientCardRepository repository;

    public ClientCardService(ClientCardRepository repository) {
        this.repository = repository;
    }

    public List<ClientCard> findCardsByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
