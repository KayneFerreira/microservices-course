package com.msstudy.ms_customers.service;

import com.msstudy.ms_customers.domain.entities.CustomerEntity;
import com.msstudy.ms_customers.domain.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    CustomerService (CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerEntity saveNewClient(CustomerEntity customer) {
        return repository.save(customer);
    }

    public Optional<CustomerEntity> getClientByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

}
