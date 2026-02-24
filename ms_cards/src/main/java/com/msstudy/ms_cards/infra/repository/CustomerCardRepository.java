package com.msstudy.ms_cards.infra.repository;

import com.msstudy.ms_cards.domain.entities.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {

    List<CustomerCard> findByCpf(String cpf);
}
