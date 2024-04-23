package com.cts.controller.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.OrderedProductsResponseDto;
import com.cts.dto.ReviewDto;
import com.cts.service.customer.ReviewService;

@RestController
@RequestMapping("/api/customer")
public class ReviewController {
	@Autowired
	public  ReviewService reviewService;
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductsResponseDto> getOrderedProductDetailsByOrderId(@PathVariable Long orderId){
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
	}
	
	
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto) throws IOException{
		ReviewDto reviewDto2 = reviewService.giveReview(reviewDto);
		if(reviewDto2==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto2);
	}
}

