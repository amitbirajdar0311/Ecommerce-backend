package com.cts.service.customer.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.dto.WishlistDto;
import com.cts.entity.Product;
import com.cts.repository.ProductRepository;
import com.cts.repository.UserRepository;
import com.cts.repository.WishlistRepository;
import com.cts.service.customer.WishlistService;
@SpringBootTest
public class WishlistServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private WishlistRepository wishlistRepository;

  @Autowired
  private WishlistService wishlistService;

  @BeforeEach
  public void setUp() {
    wishlistService = new WishlistServiceImpl(userRepository, productRepository, wishlistRepository);
  }

  

  @Test
  public void testAddProductToWishlist_ProductNotFound() {
    // Arrange
    Long userId = 1L;
    Long productId = 2L;
    WishlistDto wishlistDto = new WishlistDto(userId, productId);

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    // Act
    WishlistDto savedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);

    // Assert
    assertNull(savedWishlistDto);
  }

  @Test
  public void testAddProductToWishlist_UserNotFound() {
    // Arrange
    Long userId = 1L;
    Long productId = 2L;
    WishlistDto wishlistDto = new WishlistDto(userId, productId);

    when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act
    WishlistDto savedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);

    // Assert
    assertNull(savedWishlistDto);
  }

 
}