package com.msstudy.mscards.domain.entities;

import com.msstudy.mscards.domain.enums.CardFlag;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private CardFlag flag;
    private BigDecimal monthlyIncome;
    private BigDecimal baseLimit;

    public CardEntity(String name, CardFlag flag, BigDecimal income, BigDecimal limit) {
        this.name = name;
        this.flag = flag;
        this.monthlyIncome = income;
        this.baseLimit = limit;
    }
}
