package com.msstudy.mscards.dtos;

import com.msstudy.mscards.domain.entities.CardEntity;
import com.msstudy.mscards.domain.enums.CardFlag;
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
