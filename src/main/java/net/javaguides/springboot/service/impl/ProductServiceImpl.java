package net.javaguides.springboot.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import net.javaguides.springboot.exception.ResourseNotFoundException;
import net.javaguides.springboot.model.Product;
import net.javaguides.springboot.repository.ProductRepository;
import net.javaguides.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product saveProduct(Product product) {
			return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id) {
		
		return productRepository.findById(id).orElseThrow(()-> new ResourseNotFoundException("Product", "Id", id));
	}

	@Override
    public Product updateProduct(Product product, long id) {
		
		Product existingProduct = productRepository.findById(id).orElseThrow(
				() -> new ResourseNotFoundException("Product","Id",id));
		
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		
		productRepository.save(existingProduct);
		return null;
	}

	@Override
     public void deleteProduct(long id) {
		
		productRepository
		.findById(id).orElseThrow(() -> new ResourseNotFoundException("Employee", "Id", id));
	productRepository.deleteById(id);

}
}