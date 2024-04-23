package com.cts.service.customer;

import java.util.List;

import com.cts.dto.OrderDto;
import com.cts.dto.PlaceOrderDto;

public interface OrderService {

	
	
	
	OrderDto placedOrder(PlaceOrderDto placeOrderDto);
	
	List<OrderDto> getMyPlacedOrders(Long userId);
	

}
