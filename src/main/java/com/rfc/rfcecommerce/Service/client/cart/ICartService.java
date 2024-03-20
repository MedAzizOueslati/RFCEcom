package com.rfc.rfcecommerce.Service.client.cart;

import com.rfc.rfcecommerce.dto.AddProductToCart;
import com.rfc.rfcecommerce.dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    public ResponseEntity<?> addProductToCart(AddProductToCart addProductToCart);
    public OrderDto getCartByUserId(Long userId);

    }
