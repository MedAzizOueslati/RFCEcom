package com.rfc.rfcecommerce.service.admin.product;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.rfc.rfcecommerce.dto.ProductDto;
import com.rfc.rfcecommerce.entity.Category;
import com.rfc.rfcecommerce.entity.Product;
import com.rfc.rfcecommerce.repository.ICategoryRepo;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.service.admin.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ProductServiceImplTest {

    @Mock
    private IProductRepo productRepo;

    @Mock
    private ICategoryRepo categoryRepo;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllProducts() {
        // Arrange
        Category category = new Category();
        category.setId(1L);

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setCategory(category);

        when(productRepo.findAll()).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<ProductDto> products = productService.getAllProducts();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void getAllProductsByName() {
        // Arrange
        Category category = new Category();
        category.setId(1L);

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setCategory(category);

        when(productRepo.findAllByNameContaining("Product")).thenReturn(Arrays.asList(product1, product2));

        // Act
        List<ProductDto> products = productService.getAllProductsByName("Product");

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepo, times(1)).findAllByNameContaining("Product");
    }

    @Test
    void deleteProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepo).deleteById(1L);

        // Act
        boolean result = productService.deleteProduct(1L);

        // Assert
        assertTrue(result);
        verify(productRepo, times(1)).findById(1L);
        verify(productRepo, times(1)).deleteById(1L);
    }

    @Test
    void getProductById() {
        // Arrange
        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("Product");
        product.setCategory(category);

        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ProductDto productDto = productService.getProductById(1L);

        // Assert
        assertNotNull(productDto);
        assertEquals("Product", productDto.getName());
        verify(productRepo, times(1)).findById(1L);
    }


}
