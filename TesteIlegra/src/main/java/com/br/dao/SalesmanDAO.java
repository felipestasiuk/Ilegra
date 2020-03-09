package com.br.dao;

import com.br.entity.Salesman;
import com.br.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesmanDAO {

    @Autowired
    private SalesmanRepository repository;

    public Iterable<Salesman> findAll() {
        return repository.findAll();
    }

    public Salesman insert(Salesman salesman) { return repository.save(salesman); }

    public Salesman findByCpf(String cpf) { return repository.findByCpf(cpf); }
}
