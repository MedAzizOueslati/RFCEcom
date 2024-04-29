package com.rfc.rfcecommerce.dto;

import com.rfc.rfcecommerce.Entity.Product;
import com.rfc.rfcecommerce.Entity.User;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
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
