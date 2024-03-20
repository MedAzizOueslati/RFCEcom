package com.rfc.rfcecommerce.dto;

import lombok.Data;

@Data
public class AddProductToCart {
    private Long userId;
    private Long productId;
}
