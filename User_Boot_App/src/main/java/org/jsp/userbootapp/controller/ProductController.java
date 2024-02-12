package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/products/{user_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProdcut(@RequestBody Product product,
			@PathVariable int user_id) {
		return productService.saveProduct(product, user_id);
	}

	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateProdcut(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
		return productService.findById(id);
	}

	@GetMapping("/products")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return productService.findAll();
	}

	@GetMapping("/products/by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productService.findByBrand(brand);
	}

	@GetMapping("/products/by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productService.findByCategory(category);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<ResponseStructure<String>> delete(@PathVariable int id) {
		return productService.deleteById(id);
	}

}
