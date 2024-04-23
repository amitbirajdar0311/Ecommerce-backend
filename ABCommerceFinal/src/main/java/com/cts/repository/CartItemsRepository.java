package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.CartItem;


@Repository
public interface CartItemsRepository extends JpaRepository<CartItem, Long>{
	
	 List<CartItem> findByProductIdAndUserId(Long productId, Long userId);

	    List<CartItem> findByUserId(Long userId);
	   
}
