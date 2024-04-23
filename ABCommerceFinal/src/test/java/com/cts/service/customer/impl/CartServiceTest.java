package com.cts.service.customer.impl;
import com.cts.dto.AddProductInCartDto;
import com.cts.dto.CartItemsDto;
import com.cts.entity.CartItem;
import com.cts.entity.Product;
import com.cts.entity.User;
import com.cts.exception.CartItemsNotFoundException;
import com.cts.repository.CartItemsRepository;
import com.cts.repository.OrderRepository;
import com.cts.repository.ProductRepository;
import com.cts.repository.UserRepository;
import com.cts.service.customer.CartService;
import com.cts.service.customer.impl.CartServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemsRepository cartItemsRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Product product;
    private User user;
    private CartItem cartItem;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(100L);

        user = new User();
        user.setId(1L);

        cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(1L);
    }

    @Test
    public void testAddProductToCart() {
        AddProductInCartDto addProductInCartDto = new AddProductInCartDto();
        addProductInCartDto.setProductId(1L);
        addProductInCartDto.setUserId(1L);

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(cartItemsRepository.save(Mockito.any())).thenReturn(cartItem);

        ResponseEntity<?> responseEntity = cartService.addProductToCart(addProductInCartDto);
        Assertions.assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    public void testGetCartByUserId() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Mockito.when(cartItemsRepository.findByUserId(1L)).thenReturn(cartItems);

        List<CartItemsDto> result = cartService.getCartByUserId(1L);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void testIncreaseProductQuantity() throws CartItemsNotFoundException {
        AddProductInCartDto addProductInCartDto = new AddProductInCartDto();
        addProductInCartDto.setProductId(1L);
        addProductInCartDto.setUserId(1L);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Mockito.when(cartItemsRepository.findByProductIdAndUserId(1L, 1L)).thenReturn(cartItems);
        Mockito.when(cartItemsRepository.save(Mockito.any())).thenReturn(cartItem);

        CartItemsDto result = cartService.increaseProductQuantity(addProductInCartDto);
        Assertions.assertEquals(2L, result.getQuantity());
    }

    @Test
    public void testDecreaseProductQuantity() throws CartItemsNotFoundException {
        AddProductInCartDto addProductInCartDto = new AddProductInCartDto();
        addProductInCartDto.setProductId(1L);
        addProductInCartDto.setUserId(1L);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        Mockito.when(cartItemsRepository.findByProductIdAndUserId(1L, 1L)).thenReturn(cartItems);
        Mockito.when(cartItemsRepository.save(Mockito.any())).thenReturn(cartItem);

        CartItemsDto result = cartService.decreaseProductQuantity(addProductInCartDto);
        Assertions.assertEquals(0L, result.getQuantity());
    }
}