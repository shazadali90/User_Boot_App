package org.jsp.userbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.userbootapp.dao.ProductDao;
import org.jsp.userbootapp.dao.UserDao;
import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.dto.User;
import org.jsp.userbootapp.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int user_id) {
		Optional<User> recUser = userDao.findById(user_id);
		if (recUser.isPresent()) {
			User u = recUser.get();
			u.getProducts().add(product);
			userDao.saveUser(u);
			product.setUser(u);
			ResponseStructure<Product> structure = new ResponseStructure<>();
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		Optional<Product> recProduct = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setName(product.getName());
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setCost(product.getCost());
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<>(structure, HttpStatus.ACCEPTED);
		}
		structure.setData(null);
		structure.setMessage("Cannot Update product as Id is invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		structure.setData(null);
		structure.setMessage("Cannot find product as Id is invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findAll());
		structure.setMessage("List of all products");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findByBrand(brand));
		structure.setMessage("List of all products for " + brand);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setData(productDao.findByCategory(category));
		structure.setMessage("List of all products for " + category);
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			productDao.deleteById(id);
			structure.setData("User Found");
			structure.setMessage("User deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setData("User Not Found");
		structure.setMessage("Cannot delete product as Id is invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
