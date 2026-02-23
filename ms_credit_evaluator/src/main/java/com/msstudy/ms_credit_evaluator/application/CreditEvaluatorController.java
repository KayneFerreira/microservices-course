package com.msstudy.ms_credit_evaluator.application;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msstudy.ms_credit_evaluator.application.exceptions.ClientDataNotFoundException;
import com.msstudy.ms_credit_evaluator.application.exceptions.MicroserviceCommunicationError;
import com.msstudy.ms_credit_evaluator.domain.model.AvailabilityCheckResponse;
import com.msstudy.ms_credit_evaluator.domain.model.AvailabilityData;
import com.msstudy.ms_credit_evaluator.domain.model.CustomerStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("credit-evaluation")
@SuppressWarnings("rawtypes")
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    
    @GetMapping("test")
    public String status() {
        return "Credit evaluation OK";
    }

    
	@GetMapping(value = "customer-status", params = "cpf")
    public ResponseEntity checkCustomerStatus(@RequestParam String cpf) {
    	try {
    		CustomerStatus customerStatus = creditEvaluatorService.getCustomerStatus(cpf);
            return ResponseEntity.ok(customerStatus);
    	} 
    	catch (ClientDataNotFoundException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
		catch (MicroserviceCommunicationError e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
    }
    
	
    @PostMapping
    public ResponseEntity checkAvailability(@RequestBody AvailabilityData data) {
    	try {
    		AvailabilityCheckResponse response = creditEvaluatorService.checkAvailability(data.getCpf(), data.getIncome());
    		return ResponseEntity.status(HttpStatus.OK).body(response);
    	} 
    	catch (ClientDataNotFoundException e) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    	}
		catch (MicroserviceCommunicationError e) {
			return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
		}
    }
    
}
