package com.msstudy.ms_customers.domain.repositories;

import com.msstudy.ms_customers.domain.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCpf(String cpf);

}
