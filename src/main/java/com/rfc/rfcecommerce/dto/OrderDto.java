package com.rfc.rfcecommerce.dto;

import com.rfc.rfcecommerce.Entity.CartItems;
import com.rfc.rfcecommerce.Entity.OrderStatus;
import com.rfc.rfcecommerce.Entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {

    private Long id;
    private String orderDescption;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;

    private String userName;
    private List<CartItemsDto> cartItems;

}
