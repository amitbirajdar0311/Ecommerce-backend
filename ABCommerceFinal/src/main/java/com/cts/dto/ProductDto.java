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
public class ProductDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private Long price;
	private String description;
	private Long quantity;

	//private byte[] byteImg;
	
	private Long categoryId;
	
	private String categoryName; 
	
	//private MultipartFile img;	
	
}
