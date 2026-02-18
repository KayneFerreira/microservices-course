package com.msstudy.ms_credit_evaluator.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msstudy.ms_credit_evaluator.application.exceptions.ClientDataNotFoundException;
import com.msstudy.ms_credit_evaluator.application.exceptions.MicroserviceCommunicationError;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerCard;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerData;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerStatus;
import com.msstudy.ms_credit_evaluator.infra.clients.CardsApiClient;
import com.msstudy.ms_credit_evaluator.infra.clients.CustomerApiClient;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditEvaluatorService {
	
	private final CustomerApiClient customerClient;
	private final CardsApiClient cardClient;

    public CustomerStatus getCustomerStatus(String cpf) 
    		throws ClientDataNotFoundException, MicroserviceCommunicationError {
    	
    	try {
    		ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
        	ResponseEntity<List<CustomerCard>> cardDataResponse = cardClient.customerCardData(cpf);
        	
        	return CustomerStatus
        			.builder()
        			.customerData(customerDataResponse.getBody())
        			.customerCard(cardDataResponse.getBody())
        			.build();
        	
		} catch (FeignException.FeignClientException exception) {
			int status = exception.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new ClientDataNotFoundException(cpf);
			}
			throw new MicroserviceCommunicationError(exception.getMessage(), status);
		}
    }
    
}
