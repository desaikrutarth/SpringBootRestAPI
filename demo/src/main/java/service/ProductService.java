package service;

import java.util.List;

import entity.Product;

public interface ProductService
{
	public List<Product> retrieveProducts();

	public Product getProduct(Long id);

	public void saveProduct(Product product);

	public void deleteProduct(Long id);

	public void updateProduct(Product product);

}
