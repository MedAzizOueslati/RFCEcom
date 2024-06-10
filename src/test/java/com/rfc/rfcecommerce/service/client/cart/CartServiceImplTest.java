package com.rfc.rfcecommerce.service.client.cart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.rfc.rfcecommerce.dto.*;
import com.rfc.rfcecommerce.entity.*;
import com.rfc.rfcecommerce.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CartServiceImplTest {

    @Mock
    private IOrderRepo orderRepo;

    @Mock
    private IUserRepo userRepo;

    @Mock
    private ICartItemRepo cartItemRepo;

    @Mock
    private IProductRepo productRepo;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetCartByUserId() {
        // Préparer les données simulées
        Long userId = 1L;
        Order activeOrder = createMockOrder(userId);

        when(orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING)).thenReturn(activeOrder);

        // Appeler la méthode à tester
        OrderDto result = cartService.getCartByUserId(userId);

        // Vérifier le résultat
        assertEquals(activeOrder.getId(), result.getId());
        assertEquals(activeOrder.getAmount(), result.getAmount());
    }

    @Test
    void testIncreaseQuantity() {
        // Préparer les données simulées
        Long userId = 1L;
        Long productId = 1L;
        AddProductToCart addProductToCart = new AddProductToCart();
        addProductToCart.setUserId(userId);
        addProductToCart.setProductId(productId);

        Order activeOrder = createMockOrder(userId);
        Product product = createMockProduct(productId);
        CartItems cartItem = createMockCartItem(productId, activeOrder.getId(), userId);

        when(orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING)).thenReturn(activeOrder);
        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemRepo.findByProductIdAndOrderIdAndUserId(productId, activeOrder.getId(), userId)).thenReturn(Optional.of(cartItem));
        when(cartItemRepo.save(cartItem)).thenReturn(cartItem);
        when(orderRepo.save(activeOrder)).thenReturn(activeOrder);

        // Appeler la méthode à tester
        OrderDto result = cartService.increaseQuantity(addProductToCart);

        // Vérifier le résultat
        assertEquals(activeOrder.getId(), result.getId());
        assertEquals(activeOrder.getAmount(), result.getAmount());
    }

    @Test
    void testDecreaseQuantity() {
        // Préparer les données simulées
        Long userId = 1L;
        Long productId = 1L;
        AddProductToCart addProductToCart = new AddProductToCart();
        addProductToCart.setUserId(userId);
        addProductToCart.setProductId(productId);

        Order activeOrder = createMockOrder(userId);
        Product product = createMockProduct(productId);
        CartItems cartItem = createMockCartItem(productId, activeOrder.getId(), userId);

        when(orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING)).thenReturn(activeOrder);
        when(productRepo.findById(productId)).thenReturn(Optional.of(product));
        when(cartItemRepo.findByProductIdAndOrderIdAndUserId(productId, activeOrder.getId(), userId)).thenReturn(Optional.of(cartItem));
        when(cartItemRepo.save(cartItem)).thenReturn(cartItem);
        when(orderRepo.save(activeOrder)).thenReturn(activeOrder);

        // Appeler la méthode à tester
        OrderDto result = cartService.decreaseQuantity(addProductToCart);

        // Vérifier le résultat
        assertEquals(activeOrder.getId(), result.getId());
        assertEquals(activeOrder.getAmount(), result.getAmount());
    }

    @Test
    void testPlaceOrder() {
        // Préparer les données simulées
        Long userId = 1L;
        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUserId(userId);
        placeOrderDto.setAddress("Test Address");
        placeOrderDto.setOrderDescription("Test Description");

        Order activeOrder = createMockOrder(userId);
        User user = createMockUser(userId);

        when(orderRepo.findByUserIdAndOrderStatus(userId, OrderStatus.PENDING)).thenReturn(activeOrder);
        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepo.save(activeOrder)).thenReturn(activeOrder);

        // Appeler la méthode à tester
        OrderDto result = cartService.placeOrder(placeOrderDto);

        // Vérifier le résultat
        assertEquals(activeOrder.getId(), result.getId());
        assertEquals(activeOrder.getAmount(), result.getAmount());
    }

    @Test
    void testGetMyPlacedOrders() {
        // Préparer les données simulées
        Long userId = 1L;
        Order placedOrder = createMockOrder(userId);
        placedOrder.setOrderStatus(OrderStatus.PLACED);

        when(orderRepo.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.PLACED, OrderStatus.SHIPPED, OrderStatus.DELIVERED)))
                .thenReturn(Collections.singletonList(placedOrder));

        // Appeler la méthode à tester
        List<OrderDto> result = cartService.getMyPlacedOrders(userId);

        // Vérifier le résultat
        assertEquals(1, result.size());
        assertEquals(placedOrder.getId(), result.get(0).getId());
    }

    private Product createMockProduct(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setName("Example Product");
        product.setPrice(50L);
        return product;
    }

    private User createMockUser(Long userId) {
        User user = new User();
        user.setId(userId);
        user.setName("Example User");
        return user;
    }

    private Order createMockOrder(Long userId) {
        Order order = new Order();
        order.setId(1L);
        User user = new User();
        user.setId(userId);
        user.setName("Example User");
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(100L);
        order.setAmount(100L);
        order.setCartItems(Collections.emptyList());
        return order;
    }

    private CartItems createMockCartItem(Long productId, Long orderId, Long userId) {
        CartItems cartItem = new CartItems();
        cartItem.setProduct(createMockProduct(productId));
        cartItem.setOrder(createMockOrder(userId));
        cartItem.setUser(createMockUser(userId));
        cartItem.setQuantity(1L);
        cartItem.setPrice(50L);
        return cartItem;
    }
}