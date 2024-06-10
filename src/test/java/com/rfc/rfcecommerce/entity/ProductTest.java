package com.rfc.rfcecommerce.entity;

import com.rfc.rfcecommerce.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    private Product product;
    private Category category;
    @BeforeEach
    void setUp() {
        category = Mockito.mock(Category.class);
        Mockito.when(category.getId()).thenReturn(1L);
        Mockito.when(category.getName()).thenReturn("Electronics");

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000L);
        product.setDescription("High-end gaming laptop");
        product.setImg(new byte[]{1, 2, 3});
        product.setCategory(category);
    }

    @Test
    void testGetDto() {
        ProductDto productDto = product.getDto();

        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getPrice(), productDto.getPrice());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertArrayEquals(product.getImg(), productDto.getByteImg());
        assertEquals(category.getId(), productDto.getCategoryId());
        assertEquals(category.getName(), productDto.getCategoryName());
    }

}