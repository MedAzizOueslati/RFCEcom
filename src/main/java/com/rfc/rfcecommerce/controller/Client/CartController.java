package com.rfc.rfcecommerce.controller.Client;

import com.rfc.rfcecommerce.service.client.cart.ICartService;
import com.rfc.rfcecommerce.dto.AddProductToCart;
import com.rfc.rfcecommerce.dto.OrderDto;
import com.rfc.rfcecommerce.dto.PlaceOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<Object> addProductToCart(@RequestBody AddProductToCart addProductToCart){
        return cartService.addProductToCart(addProductToCart);
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<Object> getCartByUserId(@PathVariable Long userId){
        OrderDto orderDto = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }
    @PostMapping("/addition")
    public ResponseEntity<Object> increaseQuantity(@RequestBody AddProductToCart addProductToCart){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseQuantity(addProductToCart));
    }
    @PostMapping("/decrease")
    public ResponseEntity<Object> decreaseQuantity(@RequestBody AddProductToCart addProductToCart){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseQuantity(addProductToCart));
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Object> PlaceOrder(@RequestBody PlaceOrderDto placeOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
    }
    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDto>> getMyPlacedOrder(@PathVariable Long userId ){
        return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }

}
