package com.msstudy.msclients.service;

import com.msstudy.msclients.domain.entities.ClientEntity;
import com.msstudy.msclients.domain.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    ClientService (ClientRepository repository) {
        this.repository = repository;
    }

    public ClientEntity saveNewClient(ClientEntity client) {
        return repository.save(client);
    }

    public Optional<ClientEntity> getClientByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
