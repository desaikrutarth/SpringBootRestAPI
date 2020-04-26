package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customException.ResourceNotFoundException;
import entity.Product;
import repository.ProductRepository;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;
	
	//Get all products
	@Override
	public List<Product> retrieveProducts()
	{
		return productRepository.findAll();
	}

	//Get a product by id
	@Override
	public Product getProduct(Long id)
	{
		return productRepository.findById(id)
								.orElseThrow(ResourceNotFoundException::new);
	}

	//Create a new product
	@Override
	public void saveProduct(Product product)
	{
		productRepository.save(product);
	}

	//Delete a product
	@Override
	public void deleteProduct(Long id)
	{
		productRepository.deleteById(id);
	}

	//Update an existing product 
	@Override
	public void updateProduct(Product product)
	{
		productRepository.save(product);
	}

}
