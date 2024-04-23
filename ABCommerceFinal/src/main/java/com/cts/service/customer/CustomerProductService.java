package com.cts.service.customer;

import java.util.List;

import com.cts.dto.ProductDetailDto;
import com.cts.dto.ProductDto;



public interface CustomerProductService {

	List<ProductDto> getAllProducts();

	List<ProductDto> getAllProductsByName(String name);
	
	ProductDetailDto getProductDetailById(Long productId);
}
