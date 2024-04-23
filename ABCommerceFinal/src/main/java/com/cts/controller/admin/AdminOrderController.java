package com.cts.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.AnalyticsResponse;
import com.cts.dto.OrderDto;
import com.cts.service.admin.AdminOrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
	@Autowired
	private  AdminOrderService adminOrderService;

	
	
	@GetMapping("/placedOrders")
	public ResponseEntity<List<OrderDto>> getAllPlacedOrders() {
		return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
	}

	@GetMapping("/order/{orderId}/{status}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		OrderDto orderDto = adminOrderService.changeOrderStatus(orderId, status);
		if (orderDto == null)
			return ResponseEntity.badRequest().body("Something Went Wrong!!");

		return ResponseEntity.ok(orderDto);
	}
	
	
	

}
