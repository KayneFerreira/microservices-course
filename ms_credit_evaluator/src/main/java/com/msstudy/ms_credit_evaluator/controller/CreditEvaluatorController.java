package com.msstudy.ms_credit_evaluator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.msstudy.ms_credit_evaluator.domain.model.CustomerStatus;
import com.msstudy.ms_credit_evaluator.service.CreditEvaluatorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("credit-evaluation")
public class CreditEvaluatorController {

    private final CreditEvaluatorService creditEvaluatorService;

    @GetMapping("test")
    public String status() {
        return "Credit evaluation OK";
    }

    @GetMapping(value = "customer-status", params = "cpf")
    public ResponseEntity<CustomerStatus> checkCustomerStatus(@RequestParam String cpf) {
        CustomerStatus customerStatus = creditEvaluatorService.getCustomerStatus(cpf);
        return ResponseEntity.ok(customerStatus);
    }
    
}
