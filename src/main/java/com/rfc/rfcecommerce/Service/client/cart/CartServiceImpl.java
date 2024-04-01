package com.rfc.rfcecommerce.Service.client.cart;

import com.rfc.rfcecommerce.Entity.*;
import com.rfc.rfcecommerce.Repository.ICartItemRepo;
import com.rfc.rfcecommerce.Repository.IOrderRepo;
import com.rfc.rfcecommerce.Repository.IProductRepo;
import com.rfc.rfcecommerce.Repository.IUserRepo;
import com.rfc.rfcecommerce.dto.AddProductToCart;
import com.rfc.rfcecommerce.dto.CartItemsDto;
import com.rfc.rfcecommerce.dto.OrderDto;
import com.rfc.rfcecommerce.dto.PlaceOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    private IOrderRepo orderRepo;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ICartItemRepo cartItemRepo;
    @Autowired
    private IProductRepo productRepo;

    public ResponseEntity<?> addProductToCart(AddProductToCart addProductToCart) {
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductToCart.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemRepo.findByProductIdAndOrderIdAndUserId
                (addProductToCart.getProductId(), activeOrder.getId(), addProductToCart.getUserId());

        if (optionalCartItems.isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

        } else {
            Optional<Product> optionalProduct = productRepo.findById(addProductToCart.getProductId());
            Optional<User> optionalUser = userRepo.findById(addProductToCart.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updateCart = cartItemRepo.save(cart);
                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);
                orderRepo.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }

    }
    public OrderDto getCartByUserId(Long userId){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());

        OrderDto orderDto =new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setCartItems(cartItemsDtoList);

        return orderDto;
    }
    public OrderDto increaseQuantity(AddProductToCart addProductToCart){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductToCart.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepo.findById(addProductToCart.getProductId());
        Optional<CartItems> optionalCartItem = cartItemRepo.findByProductIdAndOrderIdAndUserId(
                addProductToCart.getProductId(),activeOrder.getId(),addProductToCart.getUserId()
        );
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem = optionalCartItem.get();
            Product  product= optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() + 1);

            cartItemRepo.save(cartItem);
            orderRepo.save(activeOrder);
            return activeOrder.getOrderDto();



        }
        return  null;
    }

    public OrderDto decreaseQuantity(AddProductToCart addProductToCart){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductToCart.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepo.findById(addProductToCart.getProductId());
        Optional<CartItems> optionalCartItem = cartItemRepo.findByProductIdAndOrderIdAndUserId(
                addProductToCart.getProductId(),activeOrder.getId(),addProductToCart.getUserId()
        );
        if(optionalProduct.isPresent() && optionalCartItem.isPresent()){
            CartItems cartItem = optionalCartItem.get();
            Product  product= optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItem.setQuantity(cartItem.getQuantity() - 1);

            cartItemRepo.save(cartItem);
            orderRepo.save(activeOrder);
            return activeOrder.getOrderDto();



        }
        return  null;
    }
    public OrderDto placeOrder(PlaceOrderDto placeOrderDto) {
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser = userRepo.findById(placeOrderDto.getUserId());
        if (optionalUser.isPresent()) {
            activeOrder.setOrderDescption(placeOrderDto.getOrderDescription());
            activeOrder.setAddress(placeOrderDto.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepo.save(activeOrder);

            Order order = new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepo.save(order);
            return activeOrder.getOrderDto();
        }
return null;
    }
}
