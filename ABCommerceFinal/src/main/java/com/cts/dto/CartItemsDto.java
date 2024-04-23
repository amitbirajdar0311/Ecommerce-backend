package com.cts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemsDto {
	private Long id;
	private Long price;
	private Long quantity;
	private Long productId;
	private String productName;
	private Long userId;
}
