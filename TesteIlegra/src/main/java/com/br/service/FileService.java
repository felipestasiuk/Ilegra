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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class FileService {

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private SalesmanDAO salesmanDAO;

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private ItemDAO itemDAO;

    private final String destino = "data/out/";

    public void createCustomer(String line) {
        String[] stringValues = line.split("ç");

        String cnpj = stringValues[1];
        String name = stringValues[2];
        String area = stringValues[3];

        Customer customer = customerDAO.findByCnpj(cnpj);

        if(Objects.isNull(customer)) {
            customer = new Customer();
            customer.setCnpj(cnpj);
        }
        customer.setName(name);
        customer.setArea(area);

        customerDAO.insert(customer);
    }

    public void createSalesman(String line) {
        String[] stringValues = line.split("ç");

        String cpf = stringValues[1];
        String name = stringValues[2];
        BigDecimal salary = BigDecimal.valueOf(Double.valueOf(stringValues[3]));

        Salesman salesman = salesmanDAO.findByCpf(cpf);

        if(Objects.isNull(salesman)) {
            salesman = new Salesman();
            salesman.setCpf(cpf);
        }
        salesman.setName(name);
        salesman.setSalary(salary);

        salesmanDAO.insert(salesman);
    }

    public void createSale(String line) {
        String[] stringValues = line.split("ç");

        String saleCode = stringValues[1];
        String salesmanName = stringValues[3];
        List<Item> itens = createItens(stringValues[1], stringValues[2]);
        BigDecimal value = sumItens(itens);

        Sale sale = saleDAO.findBySaleCode(saleCode);

        if(Objects.isNull(sale)) {
            sale = new Sale();
            sale.setSaleCode(saleCode);
        } else {
            itemDAO.deleteBy(saleCode);
        }
        sale.setSalesmanName(salesmanName);
        sale.setItens(itens);
        sale.setValue(value);

        saleDAO.insert(sale);
    }

    private List<Item> createItens(String saleCode, String line) {
        //[1-10-100,2-30-2.50,3-40-3.10]
        line = line.substring(1,line.length()-1);
        String[] stringValues = line.split(",");
        List<Item> lista = new ArrayList<Item>();

        for(int i =0; i < stringValues.length; i++){
            lista.add(createItem(stringValues[i], saleCode));
        }
        return lista;
    }

    private Item createItem(String line, String saleCode) {
        String[] stringValues = line.split("-");

        Item item = Item.builder()
                .itemCode(Integer.valueOf(stringValues[0]))
                .quantity(Integer.valueOf(stringValues[1]))
                .price(BigDecimal.valueOf(Double.valueOf(stringValues[2])))
                .saleCode(saleCode)
                .build();

        return item; //itemDAO.insert(item);
    }

    private BigDecimal sumItens(List<Item> itens) {
        BigDecimal total = BigDecimal.ZERO;
        for(Item item : itens) {
            total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    public void creteOutFile() {
        try {
            File file = new File(destino.concat("saida.txt"));

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutput = new FileOutputStream(file);

            fileOutput.write(buildFile().getBytes());
            fileOutput.flush();
            fileOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildFile() {
        Iterable<Sale> salesTeste = saleDAO.findAll();
        List<Sale> sales = saleDAO.findAll(Sort.by(Sort.Direction.ASC, "value"));
        StringBuilder builder = new StringBuilder();
        builder.append("Quantidade de clientes no arquivo de entrada: ")
                .append(quantidadeClientes())
                .append("\n")
                .append("Quantidade de vendedores no arquivo de entrada: ")
                .append(quantidadeVendedores())
                .append("\n")
                .append("ID da venda mais cara: ")
                .append(Objects.nonNull(sales) && !sales.isEmpty() ? sales.get(sales.size()-1).getSaleCode() : "")
                .append("\n")
                .append("O pior vendedor: ")
                .append(Objects.nonNull(sales) && !sales.isEmpty() ? sales.get(0).getSalesmanName() : "");
        return builder.toString();
    }

    private String quantidadeClientes() {
        Iterable<Customer> customers = customerDAO.findAll();
        if(Objects.isNull(customers)) return "0";
        return String.valueOf(IterableUtils.size(customers));
    }

    private String quantidadeVendedores() {
        Iterable<Salesman> salesmans = salesmanDAO.findAll();
        if(Objects.isNull(salesmans)) return "0";
        return String.valueOf(IterableUtils.size(salesmans));
    }

}
