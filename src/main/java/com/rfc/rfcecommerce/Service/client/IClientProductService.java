package com.rfc.rfcecommerce.Service.client;

import com.rfc.rfcecommerce.dto.ProductDto;

import java.util.List;

public interface IClientProductService {
    public List<ProductDto> getAllProducts();
    public List<ProductDto> searchAllProductsByName(String name);
}
