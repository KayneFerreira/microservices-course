package com.msstudy.mscards.dtos;

import com.msstudy.mscards.domain.entities.CardEntity;
import com.msstudy.mscards.domain.enums.CardFlag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardSaveRequest {

    private String name;
    private CardFlag flag;
    private BigDecimal monthlyIncome;
    private BigDecimal baseLimit;

    public CardEntity toModel() {
        return new CardEntity(name, flag, monthlyIncome, baseLimit);
    }
}
