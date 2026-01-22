package com.msstudy.msclients.dtos;

import com.msstudy.msclients.domain.entities.ClientEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public ClientEntity toModel() {
        return new ClientEntity(this.cpf, this.name, this.age);
    }
}
