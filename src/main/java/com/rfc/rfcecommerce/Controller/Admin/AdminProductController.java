package com.rfc.rfcecommerce.Controller.Admin;

import com.rfc.rfcecommerce.Service.admin.product.IProductService;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
    private final IProductService productService;
   @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProducts();
        return  ResponseEntity.ok(productDtos);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productDtos = productService.getAllProductsByName(name);
        return  ResponseEntity.ok(productDtos);
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
       boolean deleted = productService.deleteProduct(productId);
       if (deleted){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }
}
