package com.rfc.rfcecommerce.Service.client.cart;

import com.rfc.rfcecommerce.dto.AddProductToCart;
import com.rfc.rfcecommerce.dto.OrderDto;
import com.rfc.rfcecommerce.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    public ResponseEntity<?> addProductToCart(AddProductToCart addProductToCart);
    public OrderDto getCartByUserId(Long userId);
    public OrderDto increaseQuantity(AddProductToCart addProductToCart);

    public OrderDto decreaseQuantity(AddProductToCart addProductToCart);
    public OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    }
