package com.rfc.rfcecommerce.service.client.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rfc.rfcecommerce.dto.*;
import com.rfc.rfcecommerce.entity.*;
import com.rfc.rfcecommerce.repository.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Optional;

class CartServiceImplTest {
    @Mock
    private IOrderRepo orderRepo;
    @Mock
    private IProductRepo productRepo;
    @Mock
    private ICartItemRepo cartItemRepo;
    @InjectMocks
    private CartServiceImpl cartService;


    @Test
    void addProductToCart() {
    }

    @Test
    void getCartByUserId() {
    }

    @Test
    void increaseQuantity() {

        // Arrange
        AddProductToCart addProductToCart = new AddProductToCart();
        addProductToCart.setUserId(1L);
        addProductToCart.setProductId(1L);

        Order activeOrder = new Order();
        activeOrder.setId(1L);
        activeOrder.setAmount(100L);
        activeOrder.setTotalAmount(100L);

        Product product = new Product();
        product.setId(1L);
        product.setPrice(50L);

        CartItems cartItem = new CartItems();
        cartItem.setProduct(product);
        cartItem.setQuantity(1L);
        cartItem.setOrder(activeOrder);

        when(orderRepo.findByUserIdAndOrderStatus(1L, OrderStatus.PENDING)).thenReturn(activeOrder);
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        when(cartItemRepo.findByProductIdAndOrderIdAndUserId(1L, 1L, 1L)).thenReturn(Optional.of(cartItem));

        // Act
        OrderDto result = cartService.increaseQuantity(addProductToCart);

        // Assert
        assertNotNull(result);
        assertEquals(150L, result.getAmount());
        assertEquals(150L, result.getTotalAmount());
        assertEquals(2L, cartItem.getQuantity());
        verify(cartItemRepo, times(1)).save(cartItem);
        verify(orderRepo, times(1)).save(activeOrder);
    }



    @Test
    void decreaseQuantity() {
    }

    @Test
    void placeOrder() {
    }

    @Test
    void getMyPlacedOrders() {
    }
}