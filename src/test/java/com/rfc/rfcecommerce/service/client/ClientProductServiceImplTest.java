package com.rfc.rfcecommerce.service.client;
import com.rfc.rfcecommerce.dto.ProductDetailsDto;
import com.rfc.rfcecommerce.dto.ProductDto;
import com.rfc.rfcecommerce.entity.Category;
import com.rfc.rfcecommerce.entity.Product;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.repository.IReviewRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

 class ClientProductServiceImplTest {
    @Mock
    private IProductRepo productRepo;

    @Mock
    private IReviewRepo reviewRepo;

    @InjectMocks
    private ClientProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Préparer les données simulées
        Product product = new Product();
        product.setId(1L);
        product.setName("Example Product");
        product.setDescription("This is an example product description.");
        product.setPrice(50L);
        product.setCategory(new Category(1L, "Example Category", "This is an example category description."));
        List<Product> productList = Collections.singletonList(product);
        when(productRepo.findAll()).thenReturn(productList);

        // Appeler la méthode à tester
        List<ProductDto> result = productService.getAllProducts();

        // Vérifier le résultat
        assertEquals(productList.size(), result.size());
    }
    @Test
    void testSearchAllProductsByName() {
        // Préparer les données simulées
        String searchKeyword = "Example";
        List<Product> productList = Collections.singletonList(createMockProduct());
        when(productRepo.findAllByNameContaining(searchKeyword)).thenReturn(productList);

        // Appeler la méthode à tester
        List<ProductDto> result = productService.searchAllProductsByName(searchKeyword);

        // Vérifier le résultat
        assertEquals(productList.size(), result.size());
    }

    @Test
    void testGetProductDetailById() {
        Long productId = 1L;
        Product mockProduct = createMockProduct();
        when(productRepo.findById(productId)).thenReturn(Optional.of(mockProduct));

        ProductDetailsDto result = productService.getProductDetailById(productId);

        assertEquals(mockProduct.getId(), result.getProductDto().getId());
    }

    private Product createMockProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Example Product");
        product.setDescription("This is an example product description.");
        product.setPrice(50L);
        product.setCategory(new Category(1L, "Example Category", "This is an example category description."));
        return product;
    }
}
