package com.msstudy.ms_cards.domain.repository;

import com.msstudy.ms_cards.domain.entities.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRepository extends JpaRepository<ClientCard, Long> {

    List<ClientCard> findByCpf(String cpf);
}
