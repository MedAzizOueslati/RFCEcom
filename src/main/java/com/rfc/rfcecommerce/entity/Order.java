package com.rfc.rfcecommerce.entity;

import com.rfc.rfcecommerce.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")
public class Order {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String orderDescption;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    private List<CartItems> cartItems;
    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(this.id);
        orderDto.setOrderDescption(this.orderDescption);
        orderDto.setDate(this.date);
        orderDto.setAmount(this.amount);
        orderDto.setAddress(this.address);
        orderDto.setOrderStatus(this.orderStatus);
        orderDto.setTotalAmount( this.totalAmount);
        orderDto.setTrackingId(this.trackingId);
        orderDto.setUserName(this.user.getName());

        return orderDto;
    }


}
