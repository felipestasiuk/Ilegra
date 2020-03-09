package com.br.repository;

import com.br.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Sale findBySaleCode(String saleCode);
}
