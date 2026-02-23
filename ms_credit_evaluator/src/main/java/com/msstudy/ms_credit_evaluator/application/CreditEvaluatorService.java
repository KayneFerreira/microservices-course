package com.msstudy.ms_credit_evaluator.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.msstudy.ms_credit_evaluator.application.exceptions.ClientDataNotFoundException;
import com.msstudy.ms_credit_evaluator.application.exceptions.MicroserviceCommunicationError;
import com.msstudy.ms_credit_evaluator.domain.model.ApprovedCards;
import com.msstudy.ms_credit_evaluator.domain.model.AvailabilityCheckResponse;
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
    
    
    public AvailabilityCheckResponse checkAvailability(String cpf, Long income) 
    		throws ClientDataNotFoundException, MicroserviceCommunicationError {
    	
    	try {
    		ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
    		ResponseEntity<List<CustomerCard>> cardsResponse = cardClient.getCardByMinimumIncome(income);
    		
    		List<CustomerCard> cardList = cardsResponse.getBody();
    		
    		System.out.println("GET CARD LIST: " + cardList);
    		
    		List<ApprovedCards> approvedCardsList = cardList.stream().map(card -> {
    			CustomerData customerData = customerDataResponse.getBody();
    			
    			System.out.println("CUSTOMER DATA: " + customerData);
    			
    			BigDecimal baseLimit = card.getBaseLimit();
    			BigDecimal ageBD = BigDecimal.valueOf(customerData.getAge());
    			var factor = ageBD.divide(BigDecimal.valueOf(10));
    			BigDecimal approvedLimit = factor.multiply(baseLimit);
    			
    			ApprovedCards approvedCard = new ApprovedCards();
    			approvedCard.setCardName(card.getName());
    			approvedCard.setFlag(card.getFlag());
    			approvedCard.setApprovedLimit(approvedLimit);
    			
    			System.out.println("APPROVED CARD BUILD " + approvedCard);
    			
    			return approvedCard;
    		}).collect(Collectors.toList());
    		
    		return new AvailabilityCheckResponse(approvedCardsList);
    		
		} catch (FeignException.FeignClientException exception) {
			int status = exception.status();
			if (HttpStatus.NOT_FOUND.value() == status) {
				throw new ClientDataNotFoundException(cpf);
			}
			throw new MicroserviceCommunicationError(exception.getMessage(), status);
		}
    }
    
}
