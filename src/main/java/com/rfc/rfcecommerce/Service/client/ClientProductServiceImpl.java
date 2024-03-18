package com.rfc.rfcecommerce.Service.client;

import com.rfc.rfcecommerce.Entity.Product;
import com.rfc.rfcecommerce.Repository.IProductRepo;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl implements IClientProductService{

    private final IProductRepo productRepo;


    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public List<ProductDto> searchAllProductsByName(String name){
        List<Product> products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
