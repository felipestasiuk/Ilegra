package com.br;

import com.br.dao.CustomerDAO;
import com.br.entity.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TesteIlegraApplicationTests {

	@Autowired
	private CustomerDAO customerDAO;

	@Test
	void contextLoads() {
		String cnpj = "987654321";
		Customer customer = Customer.builder()
				.cnpj(cnpj)
				.name("Teste")
				.area("QA")
				.build();
		customerDAO.insert(customer);

		Customer customerAux = customerDAO.findByCnpj(cnpj);

		Assert.assertNotNull(customerAux);
		Assert.assertEquals(customer.getName(), customerAux.getName());
	}

}
