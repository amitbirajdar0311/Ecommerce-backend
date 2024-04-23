package com.cts.service.customer.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.OrderedProductsResponseDto;
import com.cts.dto.ProductDto;
import com.cts.dto.ReviewDto;
import com.cts.entity.CartItem;
import com.cts.entity.Order;
import com.cts.entity.Product;
import com.cts.entity.Review;
import com.cts.entity.User;
import com.cts.repository.OrderRepository;
import com.cts.repository.ProductRepository;
import com.cts.repository.ReviewRepository;
import com.cts.repository.UserRepository;
import com.cts.service.customer.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private  OrderRepository orderRepository;
	@Autowired
	private  ProductRepository productRepository;
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  ReviewRepository reviewRepository;

	
	
	
	public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
		if (optionalOrder.isPresent()) {
			orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());

			List<ProductDto> productDtoList = new ArrayList<>();
			for (CartItem cartItems : optionalOrder.get().getCartItems()) {
				ProductDto productDto = new ProductDto();
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				//productDto.setByteImg(cartItems.getProduct().getImg());

				productDtoList.add(productDto);
			}

			orderedProductsResponseDto.setProductDtoList(productDtoList);
			;
		}
		return orderedProductsResponseDto;
	}
	
	public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
        
        if(optionalProduct.isPresent() && optionalUser.isPresent()) {
            Review review = new Review();
            
            review.setDescription(reviewDto.getDescription());
            review.setRating(reviewDto.getRating());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            
            return reviewRepository.save(review).getDto();
        }
        return null;
    }
}
