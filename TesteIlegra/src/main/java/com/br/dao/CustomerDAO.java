package com.br.dao;

import com.br.entity.Customer;
import com.br.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDAO {

    @Autowired
    private CustomerRepository repository;

    public Iterable<Customer> findAll() {
        return repository.findAll();
    }

    public Customer insert(Customer customer) {
        return repository.save(customer);
    }

    public Customer findByCnpj(String cnpj) {
        return repository.findByCnpj(cnpj);
    }

}
