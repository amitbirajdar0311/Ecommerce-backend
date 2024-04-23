package com.cts.service.customer.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
    private  OrderRepository orderRepository;
	@Autowired
    private  CartItemsRepository cartItemsRepository;
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartItemsRepository cartItemsRepository) {
        this.orderRepository = orderRepository;
        this.cartItemsRepository = cartItemsRepository;
    }
    

    @Override
    public OrderDto placedOrder(PlaceOrderDto placeOrderDto) {
        // Fetch user from database based on user ID in placeOrderDto
        User user = userRepository.findById(placeOrderDto.getUserId())
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));
       List< CartItem> cartItems=cartItemsRepository.findByUserId(user.getId());

       Order order = new Order();
       order.setOrderDescription(placeOrderDto.getOrderDescription());
       order.setAddress(placeOrderDto.getAddress());
       
       for( CartItem  cartItem :cartItems) {
    	   order.setAmount(cartItem.getPrice());
    	   order.setDate(new Date());
    	   order.setOrderStatus(OrderStatus.Placed);
    	  
    	   
    	   
    	   
       }
        order.setUser(user);
        // Set other order details from placeOrderDto
        order.setCartItems(cartItems);

        // Save order to database
        Order savedOrder = orderRepository.save(order);

        // Map Order entity to OrderDto
        OrderDto orderDto = mapToOrderDto(savedOrder);

        // Return OrderDto
        return orderDto;
    }

    
	
	
    @Override
    public List<OrderDto> getMyPlacedOrders(Long userId) {
        // Retrieve orders for the given user ID
        List<Order> orders = orderRepository.findByUserId(userId);

        // Map the orders to DTOs and return
        return orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
    }

   

    private OrderDto mapToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setOrderDescription(order.getOrderDescription());
        orderDto.setDate(order.getDate());
        orderDto.setAmount(order.getAmount());
        orderDto.setAddress(order.getAddress());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setUserName(order.getUser().getName());
        orderDto.setCartItems(order.getCartItems());
        // Assuming User entity has a 'name' property
        // You can map other properties as needed

        return orderDto;
    }

    
	


}
