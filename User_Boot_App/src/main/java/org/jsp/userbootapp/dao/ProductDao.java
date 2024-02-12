package org.jsp.userbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public boolean deleteById(int id) {
		Optional<Product> recProduct = findById(id);
		if (recProduct.isPresent()) {
			productRepository.delete(recProduct.get());
			return true;
		}
		return false;
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
