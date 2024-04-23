package com.cts.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.cts.dto.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String name;
	private Long price;

	@Lob
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private Long quantity;
	
//	@Lob
//	@Column(columnDefinition = "longblob")
//	private byte[] img;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	//@JoinColumn(name = "category_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	//@JsonIgnore
	private Category category;
	public ProductDto getDto() {
		ProductDto productDto = new ProductDto();
		productDto.setId(id);
		productDto.setName(name);
		productDto.setPrice(price);
		productDto.setDescription(description);
		productDto.setQuantity(quantity);
		//productDto.setByteImg(img);
		productDto.setCategoryId(category.getId());
		productDto.setCategoryName(category.getName());
		return productDto;
	}
	
	
}
