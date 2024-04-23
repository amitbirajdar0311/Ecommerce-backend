package com.cts.service.customer.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dto.ProductDetailDto;
import com.cts.dto.ProductDto;
import com.cts.entity.Product;
import com.cts.entity.Review;
import com.cts.repository.ProductRepository;
import com.cts.repository.ReviewRepository;
import com.cts.service.customer.CustomerProductService;

@SpringBootTest
public class CustomerProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private ReviewRepository reviewRepository;

  @Autowired
  private CustomerProductService customerProductService;

 
  
  @Test
  public void testGetAllProductsByName_Success() {
    // Arrange
    String name = "Product";
    List<Product> products = new ArrayList<>();
    products.add(new Product());
    when(productRepository.findAllByNameContaining(name)).thenReturn(products);

    // Act
    List<ProductDto> productDtos = customerProductService.getAllProductsByName(name);

    // Assert
    assertEquals(1, productDtos.size());
    assertEquals("Product 1", productDtos.get(0).getName());
  }

  
}
