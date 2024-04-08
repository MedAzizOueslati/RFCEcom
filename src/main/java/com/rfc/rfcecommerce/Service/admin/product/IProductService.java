package com.rfc.rfcecommerce.Service.admin.product;

import com.rfc.rfcecommerce.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    public ProductDto addProduct(ProductDto productDto) throws IOException ;
    public List<ProductDto> getAllProducts();

    public List<ProductDto> getAllProductsByName(String name);
    public boolean deleteProduct(Long id);

    public ProductDto getProductById(Long productId);
    public ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException ;


    }
