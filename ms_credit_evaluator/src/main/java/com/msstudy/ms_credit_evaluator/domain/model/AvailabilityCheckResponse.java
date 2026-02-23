package com.msstudy.ms_credit_evaluator.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailabilityCheckResponse {

	private List<ApprovedCards> approvedCards;
	
}
