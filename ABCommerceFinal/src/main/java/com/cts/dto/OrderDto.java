package com.cts.dto;

import java.util.Date;
import java.util.List;

import com.cts.entity.CartItem;
import com.cts.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	private Long id;

	private String orderDescription;

	private Date date;

	private Long amount;

	private String address;

	private OrderStatus orderStatus;


	private String userName;
	@JsonIgnore
	private List<CartItem> cartItems;

}
