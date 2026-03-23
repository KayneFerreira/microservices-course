package com.msstudy.ms_cards.dtos;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardEmissionDataRequest {
	
	private Long cardId;
	private String cpf;
	private String address;
	private BigDecimal approvedLimit;

}