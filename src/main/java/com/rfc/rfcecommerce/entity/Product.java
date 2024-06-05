package com.rfc.rfcecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;
    @Lob
    private String Description;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "category_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDto getDto(){
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(Description);
        productDto.setByteImg(img);
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getName());
        return productDto;

    }

}
