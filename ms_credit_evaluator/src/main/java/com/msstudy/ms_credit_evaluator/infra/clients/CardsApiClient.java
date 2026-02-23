package com.msstudy.ms_credit_evaluator.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.msstudy.ms_credit_evaluator.domain.model.CustomerCard;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CardsApiClient {
	
	@GetMapping(params = "cpf")
    ResponseEntity<List<CustomerCard>> customerCardData(@RequestParam String cpf);
	
	@GetMapping(params = "income")
    ResponseEntity<List<CustomerCard>> getCardByMinimumIncome(@RequestParam Long income);

}
