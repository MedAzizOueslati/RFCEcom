package com.rfc.rfcecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long id;
    private Long rating;
    private String description;
    private MultipartFile img;

    private byte[] reImg;


    private Long userId;

    private Long productId;
    private String username;

}
