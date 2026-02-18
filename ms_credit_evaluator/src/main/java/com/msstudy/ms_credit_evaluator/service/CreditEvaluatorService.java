package com.msstudy.ms_credit_evaluator.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msstudy.ms_credit_evaluator.domain.model.CustomerCard;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerData;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerStatus;
import com.msstudy.ms_credit_evaluator.infra.clients.CardsApiClient;
import com.msstudy.ms_credit_evaluator.infra.clients.CustomerApiClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {
	
	private final CustomerApiClient customerClient;
	private final CardsApiClient cardClient;

    public CustomerStatus getCustomerStatus(String cpf) {
    	
    	ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
    	ResponseEntity<List<CustomerCard>> cardDataResponse = cardClient.customerCardData(cpf);
    	
    	return CustomerStatus
    			.builder()
    			.customerData(customerDataResponse.getBody())
    			.customerCard(cardDataResponse.getBody())
    			.build();
    }
    
}
