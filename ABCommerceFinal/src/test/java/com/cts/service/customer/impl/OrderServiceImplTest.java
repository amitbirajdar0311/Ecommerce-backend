package com.cts.service.customer.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.cts.dto.OrderDto;
import com.cts.dto.PlaceOrderDto;
import com.cts.entity.CartItem;
import com.cts.entity.Order;
import com.cts.entity.User;
import com.cts.enums.OrderStatus;
import com.cts.repository.CartItemsRepository;
import com.cts.repository.OrderRepository;
import com.cts.repository.UserRepository;
import com.cts.service.customer.OrderService;

public class OrderServiceImplTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private CartItemsRepository cartItemsRepository;

  @Mock
  private UserRepository userRepository;

  private OrderService orderService;

  @BeforeEach
  public void setUp() {
  }

  @Test
  public void testPlacedOrder_Success() {
    // Arrange
    Long userId = 1L;
    String orderDescription = "Test Order";
    String address = "Test Address";
    PlaceOrderDto placeOrderDto = new PlaceOrderDto(userId, orderDescription, address);

    User user = User.builder()
        .id(userId)
        .name("John Doe")
        .build();

    CartItem cartItem = CartItem.builder()
        .id(1L)
        .price(100L)
        .quantity(1L)
        .build();

    List<CartItem> cartItems = List.of(cartItem);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(cartItemsRepository.findByUserId(user.getId())).thenReturn(cartItems);
    when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
      Order savedOrder = invocation.getArgument(0, Order.class);
      savedOrder.setId(1L);
      return savedOrder;
    });

    // Act
    OrderDto orderDto = orderService.placedOrder(placeOrderDto);

    // Assert
    assertNotNull(orderDto);
    assertEquals(userId, orderDto.getId());
    assertEquals(orderDescription, orderDto.getOrderDescription());
    assertEquals(address, orderDto.getAddress());
    assertEquals(OrderStatus.Placed, orderDto.getOrderStatus());
    assertEquals(1, orderDto.getCartItems().size());
  }

  @Test
  public void testPlacedOrder_UserNotFound() {
    // Arrange
    Long userId = 1L;
    String orderDescription = "Test Order";
    String address = "Test Address";
    PlaceOrderDto placeOrderDto = new PlaceOrderDto(userId, orderDescription, address);

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // Act
    assertThrows(IllegalArgumentException.class, () -> orderService.placedOrder(placeOrderDto));
  }

 
}
