package com.msstudy.ms_credit_evaluator.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerCard {

	private Long id;
    private String name;
    private String flag;
    private BigDecimal baseLimit;

}
