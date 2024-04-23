package com.cts.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cts.dto.ProductDto;
import com.cts.exception.ProductNotFoundException;
import com.cts.service.admin.AdminProductService;

@RestController
@RequestMapping("/api/admin")
public class AdminProductController {

	@Autowired
	private  AdminProductService adminProductService;


	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) throws Exception {
		ProductDto productDto1 = adminProductService.addProduct(productDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	};

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProduct() {
		List<ProductDto> productDtos = adminProductService.getAllProducts();
		return ResponseEntity.ok(productDtos);
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductByName(@PathVariable String name) {
		List<ProductDto> productDtos = adminProductService.getAllProductsByName(name);
		return ResponseEntity.ok(productDtos);
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
		boolean deleted = adminProductService.deleteProduct(productId);
		//System.out.println("Deleted Success");
		//return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
		
		return deleted ? ResponseEntity.ok("Delted Success") : ResponseEntity.badRequest().body("bad  reuest");
	}

	

	@GetMapping("/product/{productId}")
	//public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {

	public ResponseEntity<?> getProductById(@PathVariable Long productId) {
		ProductDto productDto = adminProductService.getProductById(productId);
		if (productDto != null) {
			return ResponseEntity.ok(productDto);
		} else {
			return ResponseEntity.badRequest().body("Product Not Availabe in DB");
		}
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable Long productId,@RequestBody ProductDto productDto) throws  ProductNotFoundException {
		ProductDto updatedProduct = adminProductService.updateProduct(productId,productDto);
		if (updatedProduct != null) {
			return ResponseEntity.ok(productDto);
		} else {
			return ResponseEntity.badRequest().body("Give productd is Null");
		}

	}

}
