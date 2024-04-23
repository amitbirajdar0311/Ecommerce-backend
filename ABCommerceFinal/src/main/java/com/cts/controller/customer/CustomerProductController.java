package com.cts.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.ProductDetailDto;
import com.cts.dto.ProductDto;
import com.cts.service.customer.CustomerProductService;

@RestController
@RequestMapping("/api/customer")
public class CustomerProductController {
	@Autowired
	private  CustomerProductService customerProductService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProduct() {
		List<ProductDto> productDtos = customerProductService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<?> searchProducts(@PathVariable String name) {
	  List<ProductDto> productDtos = customerProductService.getAllProductsByName(name);
	  return productDtos.isEmpty() ? ResponseEntity.badRequest().body("Not Available ") : ResponseEntity.ok(productDtos);
	}


	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProductDetailById(@PathVariable Long productId) {
	 ProductDetailDto productDetailDto = customerProductService.getProductDetailById(productId);

	 return productDetailDto != null
	     ? ResponseEntity.ok(productDetailDto)
	     : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not available");
	}
	


}
