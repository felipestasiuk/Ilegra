package com.br.repository;

import com.br.entity.Salesman;
import org.springframework.data.repository.CrudRepository;

public interface SalesmanRepository extends CrudRepository<Salesman, Long> {

    Salesman findByCpf(String cpf);

}
