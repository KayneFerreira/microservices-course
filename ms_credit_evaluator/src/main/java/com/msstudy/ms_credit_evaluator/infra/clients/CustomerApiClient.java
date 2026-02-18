package com.msstudy.ms_credit_evaluator.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.msstudy.ms_credit_evaluator.domain.model.CustomerData;

@FeignClient(value = "ms-customers", path = "/customers")
public interface CustomerApiClient {

	@GetMapping(params = "cpf")
	ResponseEntity<CustomerData> customerData(@RequestParam String cpf);
	
}
