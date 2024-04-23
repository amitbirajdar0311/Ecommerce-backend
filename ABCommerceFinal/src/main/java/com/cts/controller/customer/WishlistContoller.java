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

import com.cts.dto.WishlistDto;
import com.cts.service.customer.WishlistService;

@RestController
@RequestMapping("/api/customer")
public class WishlistContoller {
	@Autowired
	private  WishlistService wishlistService;
	
		@PostMapping("/wishlist")
		public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto){
			WishlistDto posteWishlistDto = wishlistService.addProductToWishlist(wishlistDto);
			if(posteWishlistDto==null)
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("somewhing went wrong");
			return ResponseEntity.status(HttpStatus.CREATED).body(posteWishlistDto);
		}
	

	
	@GetMapping("/wishlist/{userId}")
	public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
	}
}
