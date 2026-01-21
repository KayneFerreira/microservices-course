package com.msstudy.msclient.service;

import com.msstudy.msclient.domain.entities.ClientEntity;
import com.msstudy.msclient.domain.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
