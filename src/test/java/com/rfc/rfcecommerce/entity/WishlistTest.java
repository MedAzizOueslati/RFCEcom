package com.rfc.rfcecommerce.entity;

import com.rfc.rfcecommerce.dto.WishlistDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WishlistTest {

    private Wishlist wishlist;
    private Product product;
    private User user;

    @BeforeEach
    void setUp() {
        product = Mockito.mock(Product.class);
        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(product.getImg()).thenReturn(new byte[]{1, 2, 3});
        Mockito.when(product.getName()).thenReturn("Test Product");
        Mockito.when(product.getDescription()).thenReturn("Test Description");
        Mockito.when(product.getPrice()).thenReturn(100L);

        user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(1L);

        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setProduct(product);
        wishlist.setUser(user);
    }

    @Test
    void testGetWishlistDto() {
        WishlistDto wishlistDto = wishlist.getWishlistDto();

        assertEquals(wishlist.getId(), wishlistDto.getId());
        assertEquals(product.getId(), wishlistDto.getProductId());
        assertArrayEquals(product.getImg(), wishlistDto.getReturnedImg());
        assertEquals(product.getName(), wishlistDto.getProductName());
        assertEquals(product.getDescription(), wishlistDto.getProductDescription());
        assertEquals(product.getPrice(), wishlistDto.getPrice());
        assertEquals(user.getId(), wishlistDto.getUserId());
    }

}