package com.cts.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.cts.dto.OrderDto;
import com.cts.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String orderDescription;
	private Date date;
	private Long amount;
	private String address;
	private OrderStatus orderStatus;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItem> cartItems;
	
//	public OrderDto getOrderDto() {
//		OrderDto orderDto = new OrderDto();
//		orderDto.setOrderDescription(orderDescription);
//		orderDto.setId(id);
//		orderDto.setAddress(address);
//		orderDto.setTrackingId(trackingId);
//		orderDto.setAmount(amount);
//		orderDto.setTotalAmount(totalAmount);
//		orderDto.setDate(date);
//		orderDto.setOrderStatus(orderStatus);
//		orderDto.setUserName(user.getName());
//		
//		return orderDto;
//	}
	
	public OrderDto getOrderDto() {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setDate(date);
        orderDto.setAmount(amount);
        orderDto.setAddress(address);
        orderDto.setOrderStatus(orderStatus);
        if (user != null) {
            orderDto.setUserName(user.getName());
        }
        // Optionally, you can map cartItems if needed
        // orderDto.setCartItems(mapCartItems(cartItems));
        return orderDto;
    }
	
}


