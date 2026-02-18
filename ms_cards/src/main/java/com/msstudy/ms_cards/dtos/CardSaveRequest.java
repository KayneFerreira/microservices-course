package com.msstudy.ms_cards.dtos;

import com.msstudy.ms_cards.domain.entities.CardEntity;
import com.msstudy.ms_cards.domain.enums.CardFlag;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardSaveRequest {

    private String name;

    @Enumerated(EnumType.STRING)
    private CardFlag flag;
    private BigDecimal income;
    private BigDecimal baseLimit;

    public CardEntity toModel() {
        return new CardEntity(name, flag, income, baseLimit);
    }
}
