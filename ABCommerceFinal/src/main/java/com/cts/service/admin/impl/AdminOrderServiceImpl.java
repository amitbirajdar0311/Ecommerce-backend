package com.cts.service.admin.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.OrderDto;
import com.cts.entity.Order;
import com.cts.enums.OrderStatus;
import com.cts.repository.OrderRepository;
import com.cts.service.admin.AdminOrderService;

@Service
public class AdminOrderServiceImpl  implements AdminOrderService{

	@Autowired
	private OrderRepository orderRepository;
	@Override
	public List<OrderDto> getAllPlacedOrders() {
		List<Order> orderList = orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));
		return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
	}

	@Override
	public OrderDto changeOrderStatus(Long orderId, String status) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			Order order=optionalOrder.get();
			
			if(Objects.equals(status, "Shipped")) {
				order.setOrderStatus(OrderStatus.Shipped);
			}
			else if(Objects.equals(status, "Delivered")){
				order.setOrderStatus(OrderStatus.Delivered);
			}
			return orderRepository.save(order).getOrderDto();
		}
		return null;
	}

	
	
	

}
