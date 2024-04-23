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

import com.cts.dto.AddProductInCartDto;
import com.cts.dto.CartItemsDto;
import com.cts.exception.CartItemsNotFoundException;
import com.cts.service.customer.CartService;

@RestController
@RequestMapping("/api/customer")
public class CartController {
	@Autowired
	private CartService cartService;

	@PostMapping("/cart")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
		return cartService.addProductToCart(addProductInCartDto);
	}
	

	 @GetMapping("/cart/{userId}")
	    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
	        List<CartItemsDto> cartItemsDtoList = cartService.getCartByUserId(userId);
	        return ResponseEntity.status(HttpStatus.OK).body(cartItemsDtoList);
	    }


	 @PostMapping("/addition")
	    public ResponseEntity<CartItemsDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException {
	        CartItemsDto cartItemsDto = cartService.increaseProductQuantity(addProductInCartDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
	    }

	 
	 
	    @PostMapping("/deduction")
	    public ResponseEntity<CartItemsDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException {
	        CartItemsDto cartItemsDto = cartService.decreaseProductQuantity(addProductInCartDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
	    }



}
