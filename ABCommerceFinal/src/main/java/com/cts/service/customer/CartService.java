package com.cts.service.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.cts.dto.AddProductInCartDto;
import com.cts.dto.CartItemsDto;
import com.cts.dto.OrderDto;
import com.cts.exception.CartItemsNotFoundException;


public interface CartService {

	
	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

	
	List<CartItemsDto> getCartByUserId(Long userId);
	
	
	CartItemsDto increaseProductQuantity(AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException;
	
	CartItemsDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException;


	
	
	
	
	
	

}
