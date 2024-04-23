package com.cts.service.customer.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.dto.OrderedProductsResponseDto;
import com.cts.dto.ProductDto;
import com.cts.dto.ReviewDto;
import com.cts.entity.CartItem;
import com.cts.entity.Order;
import com.cts.entity.Product;
import com.cts.entity.Review;
import com.cts.entity.User;
import com.cts.repository.OrderRepository;
import com.cts.repository.ProductRepository;
import com.cts.repository.ReviewRepository;
import com.cts.repository.UserRepository;
import com.cts.service.customer.ReviewService;

public class ReviewServiceImplTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private ReviewRepository reviewRepository;

  private ReviewService reviewService;

  
  @Test
  public void testGetOrderedProductsDetailsByOrderId_Success() {
    // Arrange
    Long orderId = 1L;
    Order order = new Order();
    order.setAmount(100L);
    List<CartItem> cartItems = new ArrayList<>();
    //cartItems.add(new CartItem(1L, 2, 50.0));
    order.setCartItems(cartItems);
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    // Act
    OrderedProductsResponseDto responseDto = reviewService.getOrderedProductsDetailsByOrderId(orderId);

    // Assert
    assertEquals(order.getAmount(), responseDto.getOrderAmount());
    assertEquals(1, responseDto.getProductDtoList().size());
    assertEquals(1L, responseDto.getProductDtoList().get(0).getId());
   // assertEquals(50.0, responseDto.getProductDtoList().get(0).getPrice());
    assertEquals(2, responseDto.getProductDtoList().get(0).getQuantity());
  }

  @Test
  public void testGetOrderedProductsDetailsByOrderId_OrderNotFound() {
    // Arrange
    Long orderId = 1L;
    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    // Act
    OrderedProductsResponseDto responseDto = reviewService.getOrderedProductsDetailsByOrderId(orderId);

    // Assert
    assertNull(responseDto.getOrderAmount());
    assertTrue(responseDto.getProductDtoList().isEmpty());
  }
  
}