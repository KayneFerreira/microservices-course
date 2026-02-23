package com.msstudy.ms_cards.dtos;

import com.msstudy.ms_cards.domain.entities.CustomerCard;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardResponse {

    private String name;

    @Enumerated(value = EnumType.STRING)
    private String flag;
    private BigDecimal approvedLimit;

    public static CustomerCardResponse fromModel(CustomerCard model) {
        return new CustomerCardResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getBaseLimit()
        );
    }
}
