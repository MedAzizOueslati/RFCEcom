package com.rfc.rfcecommerce.controller.Client;

import com.rfc.rfcecommerce.service.client.ClientProductServiceImpl;
import com.rfc.rfcecommerce.dto.ProductDetailsDto;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class ClientProductController {
    private final ClientProductServiceImpl clientProductService;


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = clientProductService.getAllProducts();
        return  ResponseEntity.ok(productDtos);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productDtos = clientProductService.searchAllProductsByName(name);
        return  ResponseEntity.ok(productDtos);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailsDto> getProductDetailById(@PathVariable Long productId){
        ProductDetailsDto productDetailDto = clientProductService.getProductDetailById(productId);
        if (productDetailDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailDto);
    }

}
