package com.msstudy.ms_cards.infra.repository;

import com.msstudy.ms_cards.domain.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByIncomeLessThanEqual(BigDecimal bdIncome);

}
