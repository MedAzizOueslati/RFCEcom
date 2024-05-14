package com.rfc.rfcecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ProductDto {
    private Long Id;
    private String name;
    private Long price;
    private String Description;

    private byte[] byteImg;
    private Long categoryId;
    private String categoryName;
    private MultipartFile img;
    private Long quantity;
}
