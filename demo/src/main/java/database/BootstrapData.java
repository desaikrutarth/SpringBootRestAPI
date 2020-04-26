package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import entity.Product;
import repository.ProductRepository;

@Component
public class BootstrapData implements CommandLineRunner
{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception
	{
		Product prod1 = new Product();
		prod1.setCategory("Electronics");
		prod1.setBrand("Apple");
		prod1.setProductName("iPhone 11");
		prod1.setPrice(999.99);
		prod1.setCurrencyCode("USD");
		productRepository.save(prod1);
		
		Product prod2 = new Product();
		prod2.setCategory("Electronics");
		prod2.setBrand("Samsung");
		prod2.setProductName("Galaxy Note");
		prod2.setPrice(499.90);
		prod2.setCurrencyCode("USD");
		productRepository.save(prod2);
		
		Product prod3 = new Product();
		prod3.setCategory("Electronics");
		prod3.setBrand("Sony");
		prod3.setProductName("Xperia 10");
		prod3.setPrice(399.90);
		prod3.setCurrencyCode("USD");
		productRepository.save(prod3);
	}

}
