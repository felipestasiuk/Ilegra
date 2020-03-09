package com.br.repository;

import com.br.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

    void deleteAllBySaleCode(String saleCode);

}
