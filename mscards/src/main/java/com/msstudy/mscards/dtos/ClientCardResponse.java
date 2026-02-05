package com.msstudy.mscards.dtos;

import com.msstudy.mscards.domain.entities.ClientCard;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCardResponse {

    private String name;

    @Enumerated(value = EnumType.STRING)
    private String flag;
    private BigDecimal approvedLimit;

    public static ClientCardResponse fromModel(ClientCard model) {
        return new ClientCardResponse(
                model.getCard().getName(),
                model.getCard().getFlag().toString(),
                model.getBaseLimit()
        );
    }
}
