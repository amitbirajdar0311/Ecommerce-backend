package com.cts.service.customer.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.dto.AddProductInCartDto;
import com.cts.dto.CartItemsDto;
import com.cts.entity.CartItem;
import com.cts.entity.Product;
import com.cts.exception.CartItemsNotFoundException;
import com.cts.repository.CartItemsRepository;
import com.cts.repository.OrderRepository;
import com.cts.repository.ProductRepository;
import com.cts.repository.UserRepository;
import com.cts.service.customer.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartItemsRepository cartItemsRepository;
	@Autowired
	private ProductRepository productRepository;
	@Override
	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto) {
	    
		// Retrieve product by ID
	    Product product = productRepository.findById(addProductInCartDto.getProductId())
	                                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

	    // Create a new cart item
	    CartItem cartItem = new CartItem();
	    cartItem.setProduct(product);
	    cartItem.setUser(userRepository.findById(addProductInCartDto.getUserId())
	                                    .orElseThrow(() -> new IllegalArgumentException("User not found")));
	    cartItem.setQuantity(1L); // Set default quantity to 1, adjust as needed
	    cartItem.setPrice(product.getPrice()); // Set the price from the product

	    // Save the cart item to the database
	    cartItem = cartItemsRepository.save(cartItem);

	    // Create a DTO to return
	    CartItemsDto cartItemsDto = new CartItemsDto();
	    cartItemsDto.setId(cartItem.getId());
	    cartItemsDto.setProductId(product.getId());
	    cartItemsDto.setQuantity(cartItem.getQuantity());
	    cartItemsDto.setUserId(cartItem.getUser().getId());
	    cartItemsDto.setProductName(product.getName());
	    cartItemsDto.setPrice(product.getPrice());

	    return ResponseEntity.status(HttpStatus.CREATED).body(cartItemsDto);
	}
		
	
	 @Override
	    public List<CartItemsDto> getCartByUserId(Long userId) {
	        List<CartItem> cartItems = cartItemsRepository.findByUserId(userId);
	        return cartItems.stream()
	                .map(CartItem::getCartDto)
	                .collect(Collectors.toList());
	    }
	 

	 @Override
	 public CartItemsDto increaseProductQuantity(AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException {
	     List<CartItem> cartItems = cartItemsRepository.findByProductIdAndUserId(addProductInCartDto.getProductId(), addProductInCartDto.getUserId());
	     
	     if (cartItems.isEmpty()) {
	         // Handle the case where no cart item is found for the given product and user
	         throw new CartItemsNotFoundException("Cart item not found");
	     }
	     
	     // Increment the quantity of each cart item by 1
	     for (CartItem cartItem : cartItems) {
	         cartItem.setQuantity(cartItem.getQuantity() + 1);
	         cartItemsRepository.save(cartItem); // Save the updated cart item
	     }
	     
	     // Return the updated cart item (you can choose to return one of the cart items, or handle this based on your requirements)
	     return cartItems.get(0).getCartDto();
	 }

	    @Override
	    public CartItemsDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto) throws CartItemsNotFoundException {
	        List<CartItem> cartItems = cartItemsRepository.findByProductIdAndUserId(addProductInCartDto.getProductId(), addProductInCartDto.getUserId());
	        
	        if (cartItems.isEmpty()) {
	            // Handle the case where no cart item is found for the given product and user
	            throw new CartItemsNotFoundException("Cart item not found");
	        }
	        
	        // Decrement the quantity of each cart item by 1
	        for (CartItem cartItem : cartItems) {
	            long updatedQuantity = cartItem.getQuantity() - 1;
	            if (updatedQuantity >= 0) {
	                cartItem.setQuantity(updatedQuantity);
	                cartItemsRepository.save(cartItem); // Save the updated cart item
	            }
	        }
	        
	        // Return the updated cart item (you can choose to return one of the cart items, or handle this based on your requirements)
	        return cartItems.get(0).getCartDto();
	    }

}