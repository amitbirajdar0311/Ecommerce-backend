package com.cts.service.admin;

import java.util.List;

import com.cts.dto.OrderDto;

public interface AdminOrderService {

	List<OrderDto> getAllPlacedOrders();

	OrderDto changeOrderStatus(Long orderId, String status);

}
