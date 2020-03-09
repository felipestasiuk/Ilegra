package com.br.dao;

import com.br.entity.Sale;
import com.br.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SaleDAO {

    @Autowired
    private SaleRepository repository;

    public Iterable<Sale> findAll() {
        return repository.findAll();
    }

    public List<Sale> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Sale insert(Sale sale) {
        return repository.save(sale);
    }

    public Sale findBySaleCode(String saleCode) { return repository.findBySaleCode(saleCode); }
}
