package com.cts.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.OrderDto;
import com.cts.dto.PlaceOrderDto;
import com.cts.service.customer.OrderService;

@RestController
@RequestMapping("/customer/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto) {
        OrderDto placedOrder = orderService.placedOrder(placeOrderDto);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId) {
        List<OrderDto> myPlacedOrders = orderService.getMyPlacedOrders(userId);
        return ResponseEntity.ok(myPlacedOrders);
    }

    
    
}
