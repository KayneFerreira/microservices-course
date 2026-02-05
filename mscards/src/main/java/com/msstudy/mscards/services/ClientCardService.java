package com.msstudy.mscards.services;

import com.msstudy.mscards.domain.entities.ClientCard;
import com.msstudy.mscards.domain.repository.ClientCardRepository;
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
