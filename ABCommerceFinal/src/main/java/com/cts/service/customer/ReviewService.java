package com.cts.service.customer;

import java.io.IOException;

import com.cts.dto.OrderedProductsResponseDto;
import com.cts.dto.ReviewDto;



public interface ReviewService {
	OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
	
	ReviewDto giveReview(ReviewDto reviewDto) throws IOException ;
}
