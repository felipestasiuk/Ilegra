package com.br.repository;

import com.br.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByCnpj(String cnpj);

}
