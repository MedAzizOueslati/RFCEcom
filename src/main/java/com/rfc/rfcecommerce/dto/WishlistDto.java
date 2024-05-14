package com.rfc.rfcecommerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WishlistDto {
    private Long userId;

    private Long productId;
    private Long id;
    private String productName;
    private String productDescription;
    private byte[] returnedImg;
    private Long price;
}
