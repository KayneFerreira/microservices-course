package com.msstudy.ms_customers.dtos;

import com.msstudy.ms_customers.domain.entities.CustomerEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public CustomerEntity toModel() {
        return new CustomerEntity(this.cpf, this.name, this.age);
    }
}
