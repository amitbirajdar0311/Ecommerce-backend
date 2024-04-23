package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Order;
import com.cts.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
	
	List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
	
	List<Order> findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);
	
	
	
	Long countByOrderStatus(OrderStatus status);

	List<Order> findByUserId(Long userId);
		
}

