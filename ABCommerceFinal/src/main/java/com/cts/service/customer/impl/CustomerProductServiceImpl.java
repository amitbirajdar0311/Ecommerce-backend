package com.cts.service.customer.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.ProductDetailDto;
import com.cts.dto.ProductDto;
import com.cts.entity.Product;
import com.cts.entity.Review;
import com.cts.repository.ProductRepository;
import com.cts.repository.ReviewRepository;
import com.cts.service.customer.CustomerProductService;

@Service
public class CustomerProductServiceImpl implements CustomerProductService {

	@Autowired
	private  ProductRepository productRepository;	
	@Autowired
	private  ReviewRepository reviewRepository;


	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	public List<ProductDto> getAllProductsByName(String name) {
		List<Product> products = productRepository.findAllByNameContaining(name);
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}

	public ProductDetailDto getProductDetailById(Long productId) {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (optionalProduct.isPresent()) {
			List<Review> reviews = reviewRepository.findAllByProductId(productId);

			ProductDetailDto productDetailDto = new ProductDetailDto();
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setReviewDtoList(reviews.stream().map(Review::getDto).collect(Collectors.toList()));

			return productDetailDto;

		}
		return null;
	}
}
