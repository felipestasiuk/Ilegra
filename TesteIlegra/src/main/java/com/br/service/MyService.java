package com.br.service;

import com.br.dao.CustomerDAO;
import com.br.dao.ItemDAO;
import com.br.dao.SaleDAO;
import com.br.dao.SalesmanDAO;
import com.br.entity.Customer;
import com.br.entity.Item;
import com.br.entity.Sale;
import com.br.entity.Salesman;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MyService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private SalesmanDAO salesmanDAO;

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private ItemDAO itemDAO;

    public Iterable<Customer> findCustomers() {
        return customerDAO.findAll();
    }

    public List<Customer> findCustomersList() {
        Iterable<Customer> customerIterable = customerDAO.findAll();
        return IterableUtils.toList(customerIterable);
    }

    public void addCustomer(String cnpj, String name, String area) {
        Customer customer = new Customer();
        customer.setCnpj(cnpj);
        customer.setName(name);
        customer.setArea(area);
        customerDAO.insert(customer);
    }

    public Iterable<Salesman> findSalesmans() {
        return salesmanDAO.findAll();
    }

    public List<Salesman> findSalesmansList() {
        Iterable<Salesman> salesmanIterable = salesmanDAO.findAll();
        return IterableUtils.toList(salesmanIterable);
    }

    public void addSalesman(String cpf, String name, BigDecimal salary) {
        Salesman salesman = new Salesman();
        salesman.setCpf(cpf);
        salesman.setName(name);
        salesman.setSalary(salary);
        salesmanDAO.insert(salesman);
    }

    public Iterable<Sale> findsales() {
        return saleDAO.findAll();
    }

    public List<Sale> findsalesList() {
        Iterable<Sale> saleIterable = saleDAO.findAll();
        return IterableUtils.toList(saleIterable);
    }

    public void addsale(String SaleCode, String salesmanName) {
        Sale sale = new Sale();
        sale.setSaleCode(SaleCode);
        sale.setSalesmanName(salesmanName);
        saleDAO.insert(sale);
    }

    public Iterable<Item> findItens() {
        return itemDAO.findAll();
    }

    public List<Item> findItensList() {
        Iterable<Item> itemIterable = itemDAO.findAll();
        return IterableUtils.toList(itemIterable);
    }

    public void addItem(Integer itemCode, Integer quantity, BigDecimal price, String saleCode) {
        Item item = new Item();
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setItemCode(itemCode);
        item.setSaleCode(saleCode);
        itemDAO.insert(item);
    }

}
