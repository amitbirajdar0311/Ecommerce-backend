package com.cts.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {

	//as customer to add to wishlist give 1 and 2
	private Long userId;
	
	private Long productId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String productName;
	
	private String productDescription;
	
//	private byte[] returnedImg;
	
	private Long price;
	public WishlistDto(Long userId2, Long productId2) {
		// TODO Auto-generated constructor stub
	}
	
}
