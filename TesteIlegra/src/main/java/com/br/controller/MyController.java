package com.br.controller;

import com.br.entity.Customer;
import com.br.entity.Sale;
import com.br.entity.Salesman;
import com.br.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ilegra")
public class MyController {

    @Autowired
    private MyService service;

    @GetMapping(path = "/customer")
    public Iterable<Customer> findCustomers() {
        return service.findCustomers();
    }

    @PostMapping(path = "/customer/insert/{cnpj}/{name}/{area}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@PathVariable("cnpj") String cnpj, @PathVariable("name") String name,
                               @PathVariable("area") String area) {
        service.addCustomer(cnpj, name, area);
    }

    @GetMapping(path = "/salesman")
    public Iterable<Salesman> findSalesmans() {
        return service.findSalesmans();
    }

    @PostMapping(path = "/salesman/insert/{cpf}/{name}/{salary}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSalesman(@PathVariable("cpf") String cpf, @PathVariable("name") String name,
                               @PathVariable("salary") BigDecimal salary) {
        service.addSalesman(cpf, name, salary);
    }

    @GetMapping(path = "/sale")
    public List<Sale> findSales() {
        return service.findsalesList();
    }

    @PostMapping(path = "/sale/insert/{saleCode}/{salesmanName}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSale(@PathVariable("saleCode") String saleCode, @PathVariable("salesmanName") String salesmanName) {
        service.addsale(saleCode, salesmanName);
    }

    @GetMapping(path = "/item")
    public Iterable<Customer> findItens() {
        return service.findCustomers();
    }

    @PostMapping(path = "/item/insert/{itemCode}/{quantity}/{price}/{saleCode}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@PathVariable("itemCode") Integer itemCode, @PathVariable("quantity") Integer quantity,
                           @PathVariable("price") BigDecimal price, @PathVariable("saleCode") String saleCode) {
        service.addItem(itemCode, quantity, price, saleCode);
    }
}
