package com.cts.service.customer;

import java.util.List;

import com.cts.dto.WishlistDto;


public interface WishlistService {
	WishlistDto addProductToWishlist( WishlistDto wishlistDto);
	
	List<WishlistDto> getWishlistByUserId(Long userId);
}
