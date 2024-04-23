package com.cts.service.admin;

import java.io.IOException;
import java.util.List;

import com.cts.dto.ProductDto;
import com.cts.exception.ProductNotFoundException;

public interface AdminProductService {

	ProductDto addProduct(ProductDto productDto) throws ProductNotFoundException;

	List<ProductDto> getAllProducts();

	List<ProductDto> getAllProductsByName(String name);

	boolean deleteProduct(Long id);

	ProductDto getProductById(Long productId);

	ProductDto updateProduct(Long productId, ProductDto productDto) throws  ProductNotFoundException;
}
