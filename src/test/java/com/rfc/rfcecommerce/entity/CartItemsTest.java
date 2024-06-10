package com.rfc.rfcecommerce.entity;

import com.rfc.rfcecommerce.dto.CartItemsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CartItemsTest {
    private CartItems cartItems;
    private Product product;
    private User user;
    @BeforeEach
    void setUp() {
        product = Mockito.mock(Product.class);
        user = Mockito.mock(User.class);

        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(product.getName()).thenReturn("Test Product");

        Mockito.when(user.getId()).thenReturn(1L);

        cartItems = new CartItems();
        cartItems.setId(1L);
        cartItems.setPrice(100L);
        cartItems.setQuantity(2L);
        cartItems.setProduct(product);
        cartItems.setUser(user);
    }
    @Test
    void testGetCartDto() {
        CartItemsDto cartItemsDto = cartItems.getCartDto();

        assertEquals(cartItems.getId(), cartItemsDto.getId());
        assertEquals(cartItems.getPrice(), cartItemsDto.getPrice());
        assertEquals(cartItems.getQuantity(), cartItemsDto.getQuantity());
        assertEquals(product.getId(), cartItemsDto.getProductId());
        assertEquals(user.getId(), cartItemsDto.getUserId());
        assertEquals(product.getName(), cartItemsDto.getProductName());
        assertEquals(product.getImg(), cartItemsDto.getReturnedImg());
    }


}