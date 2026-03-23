package com.msstudy.ms_credit_evaluator.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CardEmissionData {
	
	private Long cardId;
	private String cpf;
	private String address;
	private BigDecimal approvedLimit;

}
