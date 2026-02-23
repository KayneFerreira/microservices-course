package com.msstudy.ms_credit_evaluator.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ApprovedCards {

	private String cardName;
	private String flag;
	private BigDecimal approvedLimit;
	
}
