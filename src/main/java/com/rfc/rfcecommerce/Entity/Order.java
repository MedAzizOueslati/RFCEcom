package com.rfc.rfcecommerce.Entity;

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
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")

    private User user;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<CartItems> cartItems;
    public OrderDto getOrderDto(){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setOrderDescption(orderDescption);
        orderDto.setDate(date);
        orderDto.setAmount(amount);
        orderDto.setOrderStatus(OrderStatus.Pending);
        orderDto.setTotalAmount( getTotalAmount());
        return orderDto;
    }


}
