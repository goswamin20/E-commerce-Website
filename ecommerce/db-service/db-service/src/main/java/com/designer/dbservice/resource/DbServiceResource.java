package com.designer.dbservice.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designer.dbservice.model.Product;
import com.designer.dbservice.repository.ProductsRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {
	
	private ProductsRepository productsRepository;
	
	public DbServiceResource(ProductsRepository productsRepository) {
		this.productsRepository=productsRepository;
	}
	
	@PostMapping("/add")
	public String add(@RequestBody final Product product){
		Product p=new Product(product);
		productsRepository.save(p);
		return productsRepository.findByProductId(p.getProductId()).toString();
	}
	@PostMapping("/delete/{productId}")
	public boolean delete(@PathVariable("productId") final String productId){
		Product p=productsRepository.findByProductId(Integer.parseInt(productId));
		productsRepository.delete(p);
		return true;
	}
	
	@GetMapping("/{productId}")
	public String getProducts(@PathVariable("productId") final String productId){
		
	
		return productsRepository.findByProductId(Integer.parseInt(productId)).toString();
		
	}
	
}
