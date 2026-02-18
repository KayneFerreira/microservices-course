package com.msstudy.ms_customers.controller;

import com.msstudy.ms_customers.domain.entities.CustomerEntity;
import com.msstudy.ms_customers.dtos.CustomerSaveRequest;
import com.msstudy.ms_customers.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerController {

    private Long counter = 0L;  // Contador para teste de gateway calls

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String status() {
        counter++;
        log.info("CUSTOMER CALL: " + counter);
        return "Customer Controller OK";
    }

    @PostMapping
    public ResponseEntity<CustomerEntity> insertNewClient(@RequestBody CustomerSaveRequest request) {
        CustomerEntity customer = request.toModel();
        service.saveNewClient(customer);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(customer.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity<Optional<CustomerEntity>> customerData(@RequestParam String cpf) {
        Optional<CustomerEntity> customer = service.getClientByCpf(cpf);
        if(customer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }
}
