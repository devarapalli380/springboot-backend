package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.service.ProductService;

@RequestMapping("/api/products")
@RestController
public class ProductController {
	// controller -> service -> service imp -> repository -> entity
	// create product entity class(model class)
	// create product repository interface
	// create service interface and define methods
	// implement methods in service imp
	// create controller and write post/get/put/delete methods in controller and
	// invoke service methods
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;

	}

	@PostMapping()
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {

		return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts(); 

	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long productId) {
		return new ResponseEntity<Product>(productService.getProductById(productId), HttpStatus.OK);

	}
	
	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {

		return new ResponseEntity<Product>(productService.updateProduct(product, id), HttpStatus.OK);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully!.", HttpStatus.OK);
	}


}