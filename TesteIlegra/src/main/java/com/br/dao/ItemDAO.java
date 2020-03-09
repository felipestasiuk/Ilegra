package com.br.dao;

import com.br.entity.Item;
import com.br.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDAO {

    @Autowired
    private ItemRepository repository;

    public Iterable<Item> findAll() {
        return repository.findAll();
    }

    public Item insert(Item item) {
        return repository.save(item);
    }

    public void deleteBy(String saleCode) { repository.deleteAllBySaleCode(saleCode);}
}
