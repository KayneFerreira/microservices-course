package com.msstudy.mscards.domain.repository;

import com.msstudy.mscards.domain.entities.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByMonthlyIncomeLessThanEqual(BigDecimal bdIncome);

}
