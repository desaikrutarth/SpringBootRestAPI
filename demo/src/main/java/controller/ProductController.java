package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import entity.Product;
import service.impl.ProductServiceImpl;
import customException.ResourceNotFoundException;

@RestController
public class ProductController
{
	@Autowired
	private ProductServiceImpl productService;

	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		return productService.retrieveProducts();
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id)
	{
		 try {
			 return new ResponseEntity<Product>(productService.getProduct(id), HttpStatus.OK);
	     }
		 catch (ResourceNotFoundException e){
			 //Handling Custom Exception
			 throw new ResourceNotFoundException("Resource not Found");
	     }
	}

	@PostMapping("/createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product)
	{
	   productService.saveProduct(product);
	   return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id")Long id)
	{
		productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

	@PutMapping("/products/{id}")
	public ResponseEntity<Void> updateProduct(@RequestBody Product product, @PathVariable(name="id")Long id)
	{
		try {
			Product productInDB = productService.getProduct(id);
			if(productInDB != null)
				productService.updateProduct(product);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (ResourceNotFoundException e) {
			 //Handling Custom Exception
			 throw new ResourceNotFoundException("Resource not Found");
	     }
    }
}
