package com.msstudy.msclients.controller;

import com.msstudy.msclients.domain.entities.ClientEntity;
import com.msstudy.msclients.dtos.ClientSaveRequest;
import com.msstudy.msclients.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String status() {
        return "Client Controller OK";
    }

    @PostMapping
    public ResponseEntity<ClientEntity> insertNewClient(@RequestBody ClientSaveRequest request) {
        ClientEntity client = request.toModel();
        service.saveNewClient(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping
    public ResponseEntity<Optional<ClientEntity>> clientData(@RequestParam("cpf") String cpf) {
        Optional<ClientEntity> client = service.getClientByCpf(cpf);
        if(client.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
